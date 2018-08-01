package com.github.wsmt.reporter;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;

import java.util.Map;

public class ApplicationContext {
    private final Configuration hBaseConf;
    private final Configuration postgresConf;
    private final JavaSparkContext jsc = new JavaSparkContext("local[*]", "Reporter");
    private final SQLContext sqlContext = new SQLContext(jsc);

    private ApplicationContext(Configuration hBaseConf, Configuration postgresConf) {
        this.hBaseConf = hBaseConf;
        this.postgresConf = postgresConf;
    }

    public static ApplicationContext create(Configuration hBaseConf, Configuration postgresConf) {

//        if (args.length < 1) {
//            throw new ConfigurationException("You need to set up the configuration path into args");
//        }



        //Config config = ConfigFactory.parseFile(new File(configString));

//        Configuration hBaseConf = HBaseConfiguration.byDbName();
//        config.getConfig("hbase")
//                .entrySet()
//                .forEach(configField -> hBaseConf.set(
//                        configField.getKey(),
//                        configField.getValue()
//                                .unwrapped()
//                                .toString()
//                ));
//        Map<String, String> postgresConf = config.getConfig("postgres")
//                .entrySet()
//                .stream()
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        configField -> configField.getValue()
//                                .unwrapped()
//                                .toString()
//                ));

        return new ApplicationContext(hBaseConf, postgresConf);
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

    public Configuration getPostgresConf() {
        return postgresConf;
    }

    public void setHBase(Map<String, String> hBase) {
        hBase.forEach(hBaseConf::set);
    }
}
