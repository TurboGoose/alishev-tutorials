package ru.turbogoose;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class)) {
            System.out.println("My beans:");
            Arrays.stream(context.getBeanDefinitionNames())
                    .filter(name -> !name.startsWith("org.springframework"))
                    .forEach(System.out::println);
            MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);
            System.out.println(player);
            for (int i = 0; i < 5; i++) {
                player.playMusic();
            }
        }
    }
}
