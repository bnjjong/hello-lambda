package kr.re.kitri.hello.optionaldemo;

import kr.re.kitri.hello.majorleague.Player;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<Player> player = getPlayerObject(2_000_000);
        Player p1 = player.get();
        System.out.println(p1);

        player = getPlayerObject(0);
        Player p2 = player.orElse(new Player(2019, "EMPTY", "KR" ,"stranger", 1_000)); //null이면 인자값의 객체를 리턴 함.
        System.out.println(p2);

        player.ifPresent(System.out::println);
        player.ifPresentOrElse(System.out::println, () -> System.out.println("데이터가 없음.!"));
    }

    public static Optional<Player> getPlayerObject(long salary) {
        if (salary <= 0) {
            return Optional.empty();
        } else {
            return Optional.of(new Player(2019, "KITRI", "KR", "han" ,salary));
        }
    }
}
