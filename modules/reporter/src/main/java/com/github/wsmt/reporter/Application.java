package com.github.wsmt.reporter;

import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.util.Arrays;

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
                .groupBy(value -> value)
                .mapValues(values -> {
                    int count = 0;

                    for (String value : values) {
                        count++;
                    }

                    return count;
                })
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .mapToPair(Tuple2::swap)
                .map(valueStatistics -> RowFactory.create(valueStatistics._1(), valueStatistics._2()));

        final StructType schema = DataTypes.createStructType(
                Arrays.asList(
                        DataTypes.createStructField("url", DataTypes.StringType, false),
                        DataTypes.createStructField("count", DataTypes.IntegerType, false)
                )
        );

        applicationConfig.getSqlContext()
                .createDataFrame(result, schema)
                .registerTempTable("url");

        applicationConfig.getSqlContext()
                .sql("SELECT url, COUNT(url) AS count FROM url GROUP BY url ORDER BY count DESC")
                .write()
                .format("jdbc")
                .options(applicationConfig.getPostgresConf())
                .mode(SaveMode.Append)
                .save();
    }
}
