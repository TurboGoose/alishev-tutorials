package ru.turbogoose;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ru.turbogoose")
@PropertySource("musicPlayer.properties")
public class SpringConfig {
}
