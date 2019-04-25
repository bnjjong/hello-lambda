package kr.re.kitri.hello.dish;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class GenerateDemo {
    public static void main(String[] args) {
        OptionalDouble count = DoubleStream.generate(Math::random)
                .peek(System.out::println)
//                .filter(x -> x > 0.5)
                .limit(1000)
                .average();

        OptionalDouble optionalDouble = Stream.generate(() -> 1)
                .limit(100)
                .mapToInt(n -> n + n) // Stream<Integer> -> IntStream
                .average();


        System.out.println(count);
        System.out.println(optionalDouble);

    }
}
