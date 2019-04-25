package kr.re.kitri.hello.collect;

import kr.re.kitri.hello.majorleague.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

public class Grouping01 {

    public static void main(String[] args) throws IOException {
        Path file = Paths.get("C:\\Users\\kitri-19\\IdeaProjects\\hello-java8\\src\\main\\resources\\Salaries.csv");
//        Path file = Paths.get("/Salaries.csv");
//        Files.lines(file).forEach(item -> System.out.println(item));

        Map<String, List<Player>> collect = Files.lines(file)
                .skip(1)
                .map(str -> {
                    String[] splitStr = str.split(",");
                    return new Player(Integer.parseInt(splitStr[0]), splitStr[1], splitStr[2], splitStr[3], Long.parseLong(splitStr[4]));
                })
                .filter(a -> a.getSalary() > 10_000_000)
                .collect(Collectors.groupingBy(Player::getLeague));

        System.out.println(collect.keySet()); // AL, NL

        // 연도로 그룹핑
        Map<Integer, List<Player>> collect1 = Files.lines(file)
                .skip(1)
                .map(str -> {
                    String[] splitStr = str.split(",");
                    return new Player(Integer.parseInt(splitStr[0]), splitStr[1], splitStr[2], splitStr[3], Long.parseLong(splitStr[4]));
                })
                .filter(a -> a.getSalary() > 10_000_000)
//                .collect(Collectors.groupingBy(Player::getTeam)); // [TEX, WAS, CHN, FLO, STL, PHI, TBA, CHW, SLN, SFG, NYA, SFN, SDN, SDP, COL, NYN, NYM, MIA, TBR, BOS, KCA, CIN, LAA, MON, BAL, LAD, SEA, NYY, HOU, MIL, DET, WSN, MIN, ANA, TOR, OAK, ARI, LAN, ATL, PIT, KCR, CLE, CHA, CHC]
                .collect(Collectors.groupingBy(Player::getYear));

        System.out.println(collect1.keySet());

        // 연도별 평균 연봉.
        Map<Integer, LongSummaryStatistics> collect2 = Files.lines(file)
                .skip(1)
                .map(str -> {
                    String[] splitStr = str.split(",");
                    return new Player(Integer.parseInt(splitStr[0]), splitStr[1], splitStr[2], splitStr[3], Long.parseLong(splitStr[4]));
                })
                .filter(a -> a.getSalary() > 10_000_000)
                .collect(Collectors.groupingBy(
                        Player::getYear,
                        Collectors.summarizingLong(Player::getSalary))
                );

        System.out.println(collect2.keySet());
        LongSummaryStatistics aDouble = collect2.get(2016);
        System.out.println("max : "+aDouble.getMax());
        System.out.println("average : "+aDouble.getAverage());
        System.out.println("count : "+aDouble.getCount());

        // 연봉 1000만불 기준으로 그룹핑
        Map<Boolean, List<Player>> collect3 = Files.lines(file)
                .skip(1)
                .map(str -> {
                    String[] splitStr = str.split(",");
                    return new Player(Integer.parseInt(splitStr[0]), splitStr[1], splitStr[2], splitStr[3], Long.parseLong(splitStr[4]));
                })
//                .filter(a -> a.getSalary() > 10_000_000)
                .collect(Collectors.partitioningBy(a -> a.getSalary() >= 10_000_000) );
//                .entrySet()
//                .stream()
//                .collect(Collectors.groupingBy());

    }
}
