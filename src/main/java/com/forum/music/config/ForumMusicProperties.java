package com.forum.music.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@ConfigurationProperties(prefix = "forum")
public class ForumMusicProperties {

    @NestedConfigurationProperty
    private DataBase database = new DataBase();


    @Data
    public class DataBase{

        @NestedConfigurationProperty
         private DataBaseInfo mysql = new DataBaseInfo();
    }

    @Data
    public class DataBaseInfo {
        private String datasourceUrl;
        private String jdbcDriver;
        private String testSql;
        private String dialect;
        private String user;
        private String password;
    }
}
