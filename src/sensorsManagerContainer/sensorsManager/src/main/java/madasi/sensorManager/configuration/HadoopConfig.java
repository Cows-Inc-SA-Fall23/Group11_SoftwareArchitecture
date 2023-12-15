package madasi.sensorManager.configuration;

import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HadoopConfig {

    @Value("${hadoop.name-node.url}")
    private String nameNodeUrl;

   
    @Bean
    public Configuration hadoopConfiguration() {
        Configuration config = new Configuration();
        config.set("fs.defaultFS", nameNodeUrl);
        config.set("HADOOP_USER_NAME", "root");
//        config.set("dfs.client.use.datanode.hostname", "true");
        
        // Set other Hadoop configurations, if needed
        return config;
    }
}
