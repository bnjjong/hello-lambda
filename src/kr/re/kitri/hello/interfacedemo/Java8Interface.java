package kr.re.kitri.hello.interfacedemo;

public interface Java8Interface {
    int add(int a, int b);
    int sub(int a, int b);

    // 인터페이스에서 구현이 가능해 짐.
    default int multiply(int a, int b){
        return a * b;
    }

    static double divide(double a, double b) {
        return a /b ;
    }

}