package com.forum.music;

import com.forum.music.config.ForumMusicProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties({ForumMusicProperties.class})
public class ForumMusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumMusicApplication.class, args);
	}

}
