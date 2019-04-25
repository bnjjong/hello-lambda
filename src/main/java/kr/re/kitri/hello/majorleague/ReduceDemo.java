package kr.re.kitri.hello.majorleague;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReduceDemo {
    public static void main(String[] args) throws IOException {
        Path file = Paths.get("C:\\Users\\kitri-19\\IdeaProjects\\hello-java8\\src\\main\\resources\\Salaries.csv");

        // 보통 집계 함수를 사용 함. sum으로 하면 간단해짐.
        long totalSalary = Files.lines(file)
                .skip(1)
                .map(str -> {
                    String[] splitStr = str.split(",");
                    return new Player(Integer.parseInt(splitStr[0]), splitStr[1], splitStr[2], splitStr[3], Long.parseLong(splitStr[4]));
                })
                .limit(20)
                .mapToLong(Player::getSalary)
                .reduce(0L, Long::sum);

        System.out.println("리듀스한 전체 임금 합계 : "+totalSalary);


    }
}
