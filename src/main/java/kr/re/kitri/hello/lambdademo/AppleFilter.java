package kr.re.kitri.hello.lambdademo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class AppleFilter {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                new Apple("red", 120),
                new Apple("green", 170),
                new Apple("red", 145),
                new Apple("green", 130),
                new Apple("red", 155),
                new Apple("red", 210),
                new Apple("green", 160)
        );
        // 녹색 사과 and 150 gram 이상 필터링
//        List<Apple> greenApples = filterApples(apples, new ApplePredicate(){
//            @Override
//            public boolean test(Apple apple) {
//                return apple.getColor().equals("green") && apple.getWeight() >= 150;
//            }
//        });

        // 람다로 변환
        List<Apple> greenApples =
                filterApples(apples, apple -> apple.getColor().equals("green") && apple.getWeight() >= 150);


        // predicate 사용.
        Predicate<Apple> filtered = apple -> apple.getColor().equals("green");
//        filtered.negate();
        greenApples = filterApples2(apples, filtered
                                                        .and(apple -> apple.getWeight()>=150)
                                                        .negate()
                                                        .or(apple -> apple.getWeight() >= 200)
        );


        System.out.println(greenApples);

    }
    public static List<Apple> filterApples(List<Apple>apples, ApplePredicate ap) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : apples) {
            if (ap.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApples2(List<Apple>apples, Predicate<Apple> ap) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : apples) {
            if (ap.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

    @FunctionalInterface // 추상 메서드를 2개 이상 가지면 컴파일 에러 발생!!
    interface ApplePredicate {
        boolean test(Apple apple);
    }
}
