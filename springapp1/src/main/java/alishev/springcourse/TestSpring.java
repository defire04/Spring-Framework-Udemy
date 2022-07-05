package alishev.springcourse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestSpring {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContext.xml"
//        );

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );
//        Music music = context.getBean("rockMusic", Music.class);

//        ClassicalMusic music1 = context.getBean("classicalMusic", ClassicalMusic.class);


        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
//        musicPlayer.playMusic(GenreOfMusic.ROCK);


        System.out.println(musicPlayer.getName() + " " + musicPlayer.getVolume());
//        List<Music> musicList = List.of(music, music1);

//        MusicPlayer musicPlayer = new MusicPlayer(music1);
//        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
//        musicPlayer.playMusic();
//
//        System.out.println(musicPlayer.getName() + "\n" + musicPlayer.getVolume());
        ClassicalMusic cl = context.getBean("classicalMusic", ClassicalMusic.class);

        context.close();
    }
}
