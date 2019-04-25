package kr.re.kitri.hello.lambdademo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("kim","whang","yu","choi");
        // 1. 외부 클래스 사용
        list.sort(new MyComparator());

        // 2. inner class 선언
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        // 추상메소드가 하나인 인터페이스는 람다로 변경할 수 있음
        // 파라미터 타입이 같으면 생략 가능 (변수의 타입을 추론가능 함)
        // 바디 브레이스 생략 가능하고 바로 리턴하는 경우에는 return 도 생략 가능 함.
        // 브레이스가 없으면 return 을 생략 함.
        // 한 라인으로 표현가능하다면 브레이스 제거 가능
        // 3. 람다 표현식
        list.sort((o1, o2) -> o1.length() - o2.length());

        // 4. 변수로도 가질 수 있음
        Comparator<String> myLambda = (o1, o2) -> o1.length() - o2.length();

        // 5. var 로 선언이 가능 함. 클래스 추론이 가능해짐. 이건 안되네.
//        var myLambda2 = (String o1, String o2) -> o1.length() - o2.length();
        list.sort(myLambda);

        System.out.println(list);
    }

    static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length(); // 양수이며 1번이 크다는 의미 임.
        }
    }
}
