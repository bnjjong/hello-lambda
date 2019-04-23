package kr.re.kitri.hello.interfacedemo;

public class Calculator implements Java8Interface {

    @Override
    public int add(int a, int b) {
        return 0;
    }

    @Override
    public int sub(int a, int b) {
        return 0;
    }

    public void test() {
        multiply( 2, 3);
        Java8Interface.divide(6,2);
    }
}
