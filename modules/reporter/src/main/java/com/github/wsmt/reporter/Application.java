package com.github.wsmt.reporter;

import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

import java.util.Collections;

public class Application {
    public static void main(String[] args) {
        final ApplicationContext applicationConfig = ApplicationContext.read(args);

        final JavaRDD<Row> result = applicationConfig.getJsc()
                .newAPIHadoopRDD(
                        applicationConfig.getHBaseConf(),
                        TableInputFormat.class,
                        ImmutableBytesWritable.class,
                        Result.class
                )
                .values()
                .map(Result::listCells)
                .flatMap(Iterable::iterator)
                .map(CellUtil::cloneValue)
                .map(Bytes::toString)
                .map(RowFactory::create);

        final StructType schema = DataTypes.createStructType(
                Collections.singletonList(
                        DataTypes.createStructField("url", DataTypes.StringType, false)
                )
        );

        applicationConfig.getSqlContext()
                .createDataFrame(result, schema)
                .registerTempTable("url");

        applicationConfig.getSqlContext()
                .sql("SELECT url, COUNT(url) AS count FROM url GROUP BY url ORDER BY count DESC")
                .show(false);
    }
}
