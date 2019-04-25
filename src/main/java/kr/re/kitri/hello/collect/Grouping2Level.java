package kr.re.kitri.hello.collect;

import kr.re.kitri.hello.majorleague.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Grouping2Level {
    public static void main(String[] args) throws IOException {
        Path file = Paths.get("C:\\Users\\kitri-19\\IdeaProjects\\hello-java8\\src\\main\\resources\\Salaries.csv");
        Map<String, Map<Boolean, List<Player>>> collect = Files.lines(file)
                .skip(1)
                .map(str -> {
                    String[] splitStr = str.split(",");
                    return new Player(Integer.parseInt(splitStr[0]), splitStr[1], splitStr[2], splitStr[3], Long.parseLong(splitStr[4]));
                })
                .collect(Collectors.groupingBy(
                        Player::getTeam
                        , Collectors.partitioningBy(player -> player.getSalary() >= 10_000_000)
                        )
                );

        System.out.println(collect.keySet());
        System.out.println(collect.get("TEX").get(false));


        // 최대 값 최소 값
        Map<String, Long> collect1 = Files.lines(file)
                .skip(1)
                .map(str -> {
                    String[] splitStr = str.split(",");
                    return new Player(Integer.parseInt(splitStr[0]), splitStr[1], splitStr[2], splitStr[3], Long.parseLong(splitStr[4]));
                })
                .collect(Collectors.groupingBy(s -> {
                            if (s.getSalary() >= 10_000_000)
                                return "high";
                            else if (s.getSalary() >= 5_000_000)
                                return "mid";
                            else
                                return "low";


                        }, Collectors.counting())
                );

        System.out.println(collect1);


        // summarizing
        Map<String, DoubleSummaryStatistics> collect2 = Files.lines(file)
                .skip(1)
                .map(str -> {
                    String[] splitStr = str.split(",");
                    return new Player(Integer.parseInt(splitStr[0]), splitStr[1], splitStr[2], splitStr[3], Long.parseLong(splitStr[4]));
                })
                .collect(Collectors.groupingBy(s -> {
                            if (s.getSalary() >= 10_000_000)
                                return "high";
                            else if (s.getSalary() >= 5_000_000)
                                return "mid";
                            else
                                return "low";
                        }, Collectors.summarizingDouble(Player::getSalary))
                );


        System.out.printf("평균 연봉 : %.3f \r\n", collect2.get("high").getAverage());
        System.out.printf("최고 연봉 : %.0f \r\n", collect2.get("high").getMax());
        System.out.printf("최소 연봉 : %.0f \r\n", collect2.get("high").getMin());
        System.out.println(collect2);


    }
}
