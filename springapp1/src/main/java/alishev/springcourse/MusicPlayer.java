package alishev.springcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class MusicPlayer {

    private List<Music> musicList;
    private Music music;
    private Music music2;
    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;


    public MusicPlayer() {
    }

    // Inversion of  Control IoC
    public MusicPlayer(List<Music> musicList) {
        this.musicList = musicList;
    }

    @Autowired
    public MusicPlayer(@Qualifier("classicalMusic") Music music,
                       @Qualifier("rockMusic") Music music2) {
        this.music = music;
        this.music2 = music2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;

    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public void playMusicList() {
        musicList.forEach(music -> System.out.println(music.getSong()));
    }

    public void playMusic(GenreOfMusic genre) {

        int randomSong = (int) (Math.random() * 3);
        if(genre == GenreOfMusic.ROCK){
            System.out.println(music2.getSong().get(randomSong));
        } else{
            System.out.println(music.getSong().get(randomSong));
        }

    }
}
