package com.github.wsmt.reporter.service.impl;

import com.github.wsmt.reporter.ApplicationContext;
import com.github.wsmt.reporter.handler.RowHandlerFactory;
import com.github.wsmt.reporter.service.ReportService;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

import java.util.Collections;


public class ReportServiceImpl implements ReportService {

    private final ApplicationContext applicationConfig;
    private String hBaseScanColumnConf = "hbase.mapreduce.scan.columns";

    private ReportServiceImpl(ApplicationContext applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    public static ReportServiceImpl create(ApplicationContext applicationConfig) {
        return new ReportServiceImpl(applicationConfig);
    }

    @Override
    public void report() {
        save("url", "referer");
        save("browser", "user-agent");
        save("country", "host");
        save("day", "datetime");
        save("time", "datetime");
    }

    private void save(String column, String hbaseColumn) {

        applicationConfig.getHBaseConf().set(hBaseScanColumnConf, "HEADER:" + hbaseColumn);

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
                .map(value -> RowHandlerFactory.getHandler(column).handle(Bytes.toString(value)))
                .map(RowFactory::create);

        final StructType schema = DataTypes.createStructType(
                Collections.singletonList(
                        DataTypes.createStructField(column, DataTypes.StringType, false)
                )
        );

        applicationConfig.getSqlContext()
                .createDataFrame(result, schema)
                .registerTempTable(column);


        applicationConfig.getSqlContext()
                .sql("SELECT " + column + ", COUNT(" + column + ") AS count FROM " + column +
                        " GROUP BY " + column + " ORDER BY count DESC")
                .withColumn("report", functions.lit(System.currentTimeMillis()).cast("timestamp"))
                //.show();
                .write()
                .format("jdbc")
                .option("url", "jdbc:postgresql://postgres:5432/wsmt")
                .option("user", "wsmt")
                .option("password", "wsmt")
                .option("dbtable", "statistics." + column)
                .mode(SaveMode.Append)
                .save();
    }
}
