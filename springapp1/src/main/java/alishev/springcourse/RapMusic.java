package alishev.springcourse;

import java.util.List;

public class RapMusic implements Music{

    private List<String> musicList = List.of(
            "Rap Music  1",
            "Rap Music  2",
            "Rap Music  3"
    );
    @Override
    public List<String> getSong() {
        return musicList;
    }
}
