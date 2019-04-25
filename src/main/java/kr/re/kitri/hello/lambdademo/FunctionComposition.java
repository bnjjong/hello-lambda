package kr.re.kitri.hello.lambdademo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionComposition {
    public static void main(String[] args) {
        Function<String, String> upperFunction = a -> a.toUpperCase();
        Function<String, String> addCharFunction = a -> a + "...";
        Function<String, String> lowerFunction = a -> a.toLowerCase();

        List<String> data = Arrays.asList("kim","lee","han");
        List<String> newData = mapString(data, upperFunction);
        System.out.println(newData);

        List<String> newData2 = mapString(data, addCharFunction);
        System.out.println(newData2);

        // 앞에꺼는 먼저 실행 함수의 조합
        List<String> newData3 = mapString(data, upperFunction.andThen(addCharFunction));
        System.out.println(newData3);

        // 뒤에꺼를 먼저 실행
        List<String> newData4 = mapString(data, lowerFunction.compose(upperFunction));
        System.out.println(newData4);

    }

    public static List<String> mapString(List<String> list, Function<String,String> f) {
        List<String> result = new ArrayList<>();
        for (String item : list) {
            result.add(f.apply(item));
        }
        return result;
    }
}
