package com.github.wsmt.reporter;

import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Properties;

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

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "wsmt");
        connectionProperties.put("password", "wsmt");

        Dataset<Row> dataset = applicationConfig.getSqlContext()
                .createDataFrame(result, schema);

        dataset.show(false);

        dataset.write()
                .format("jdbc")
                .option("url", "jdbc:postgresql://postgres:5432/wsmt")
                .option("dbtable", "statistics.urls")
                .option("user", "wsmt")
                .option("password", "wsmt")
                .mode(SaveMode.Overwrite)
                .save();


//                .write()
//                .jdbc("jdbc:postgresql://localhost:5432/wsmt",
//                        "statistics.urls",
//                        connectionProperties);

//                .registerTempTable("statistics");
    }
}
