package com.github.wsmt.reporter;

import com.typesafe.config.ConfigFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;

import java.io.File;
import java.util.Map;

public class ApplicationContext {
    private final Configuration hBaseConf;
    private final JavaSparkContext jsc = new JavaSparkContext("local[*]", "Reporter");
    private final SQLContext sqlContext = new SQLContext(jsc);

    private ApplicationContext(Configuration hBaseConf) {
        this.hBaseConf = hBaseConf;
    }

    public static ApplicationContext read(String[] args) {
        if (args.length < 1) {
            throw new ConfigurationException("You need to set up the configuration path into args");
        }

        Configuration hBaseConf = HBaseConfiguration.create();

        ConfigFactory.parseFile(new File(args[0]))
                .getConfig("hbase")
                .entrySet()
                .forEach(configField -> hBaseConf.set(
                        configField.getKey(),
                        configField.getValue()
                                .unwrapped()
                                .toString()
                ));

        return new ApplicationContext(hBaseConf);
    }

    public JavaSparkContext getJsc() {
        return jsc;
    }

    public SQLContext getSqlContext() {
        return sqlContext;
    }

    public Configuration getHBaseConf() {
        return hBaseConf;
    }

    public void setHBase(Map<String, String> hBase) {
        hBase.forEach(hBaseConf::set);
    }
}
