package alishev.springcourse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component

public class ClassicalMusic implements Music {

    private List<String> musicList = List.of(
            "Classical Music 1",
            "Classical Music 2",
            "Classical Music 3"
    );
//    private ClassicalMusic() {
//    }
//
//    public static ClassicalMusic getClassicalMusic() {
//        return new ClassicalMusic();
//    }
    @PostConstruct
    public void doMyInit() {
        System.out.println("Do my initialization!");
    }
    @PreDestroy
    public void destroy() {
        System.out.println("Do my destruction!");
    }

    @Override
    public List<String> getSong() {
        return musicList;
    }
}
