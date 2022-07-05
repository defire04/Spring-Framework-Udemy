package alishev.springcourse;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RockMusic implements Music {

    private List<String> musicList = List.of(
            "Rock Music  1",
            "Rock Music  2",
            "Rock Music  3"
    );

    @Override
    public List<String> getSong() {
        return musicList;
    }
}
