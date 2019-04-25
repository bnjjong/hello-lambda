package kr.re.kitri.hello.majorleague;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MajorAnalyzer {

    public static void main(String[] args) throws IOException {
        Path file = Paths.get("C:\\Users\\kitri-19\\IdeaProjects\\hello-java8\\src\\main\\resources\\Salaries.csv");
//        Path file = Paths.get("/Salaries.csv");
//        Files.lines(file).forEach(item -> System.out.println(item));

        Files.lines(file)
                .skip(1)
                .map(str -> {
                    String[] splitStr = str.split(",");
                    return new Player(Integer.parseInt(splitStr[0]), splitStr[1], splitStr[2], splitStr[3], Long.parseLong(splitStr[4]));
                })
                .filter(s -> s.getSalary() > 30_000_000)
                .peek(System.out::println)
                .mapToLong(Player::getSalary)
//                .boxed() // 다시 박싱하고 싶을 때
//                .average()
                .sorted()
                .forEach(System.out::println);


        Files.lines(file)
                .skip(1)
                .map(str -> {
                    String[] splitStr = str.split(",");
                    return new Player(Integer.parseInt(splitStr[0]), splitStr[1], splitStr[2], splitStr[3], Long.parseLong(splitStr[4]));
                })
                .filter(s -> s.getSalary() > 30_000_000)
                .peek(System.out::println)
                .mapToLong(Player::getSalary)
                .boxed() // 다시 박싱하고 싶을 때
                .sorted((a,b) -> (int) (b-a))
                .forEach(System.out::println);

        // 2000년 이후 내셔널 리그의 연봉 탑 5 출력
        Files.lines(file)
                .skip(1)
                .map(str -> {
                    String[] splitStr = str.split(",");
                    return new Player(Integer.parseInt(splitStr[0]), splitStr[1], splitStr[2], splitStr[3], Long.parseLong(splitStr[4]));
                })
                .filter(s-> s.getYear() >= 2000)
                .filter(s->s.getLeague().equals("NL"))
                .sorted((a,b) -> (int) (b.getSalary() - a.getSalary()))
                .limit(5)
                .forEach(System.out::println);

        // 아메리칸 리그에 연봉 3천만 달러 이상인 선수가 있는지?
        boolean hasHighSalary = Files.lines(file)
                .skip(1)
                .map(str -> {
                    String[] splitStr = str.split(",");
                    return new Player(Integer.parseInt(splitStr[0]), splitStr[1], splitStr[2], splitStr[3], Long.parseLong(splitStr[4]));
                })
                .anyMatch(a -> a.getLeague().equals("AL") && a.getSalary() > 30_000_000 );

        System.out.println("아메리칸리그에 연봉 3천만 달러 이상이 있음? : " + hasHighSalary);
        // 최종 연산


//        System.out.println(sum);

//        List<Player> collect = Files.lines(file)
//                .skip(1)
//                .map(str -> {
//                    String[] splitStr = str.split(",");
//                    return new Player(Integer.parseInt(splitStr[0]), splitStr[1], splitStr[2], splitStr[3], Long.parseLong(splitStr[4]));
//                })
//                .filter(s-> s.getSalary() > 1_000_000)
//                .forEach(System.out::println);




    }
}
