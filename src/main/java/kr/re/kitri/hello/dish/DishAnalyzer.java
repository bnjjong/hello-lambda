package kr.re.kitri.hello.dish;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DishAnalyzer {
    public static void main(String[] args) {
        List<Dish> dishes = Dish.menu;


        List<String> collect = dishes.stream()
                .filter(dish -> dish.getCalories() >= 400) // 400 칼로리 이상
                .sorted(Comparator.comparingInt(Dish::getCalories)) // 칼로리로 정렬 (a,b) -> a.getCalories() - b.getCalories()
                .map(Dish::getName) // 요리를 이름으로 바꿈 dish -> dish.getName()
                .limit(3)
                .collect(Collectors.toList());// 상위 3개만 출력
        System.out.println(collect);


    }
}


