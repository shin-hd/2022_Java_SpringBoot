package ch7.item42;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

/*
 * item42. 익명 클래스보다는 람다를 사용하라
 *
 *  예전에는 자바에서 함수 타입을 표현할 때
 * 추상 메소드를 하나만 담은 인터페이스를 사용 : 함수 객체
 * JDK 1.1부터 함수 객체를 만드는 주요 수단은 익명 클래스가 됨
 * 하지만 익명 클래스 방식은 코드가 너무 길어서
 * 자바는 함수형 프로그래밍에 적합하지 않았음
 *  자바 8에 와서 추상 메소드 하나짜리 인터페이스는
 * 특별한 의미를 인정받아 함수형 인터페이스라 부르고,
 * 이 인터페이스 인스턴스를 람다식을 사용해 만들 수 있음
 * 람다는 함수나 익명 클래스와 개념은 비슷하지만 코드는 훨씬 간결함
 * 42-1 -> 42-2
 * 타입을 명시해야 코드가 더 명확할 때를 제외하고,
 * 람다식에서 모든 매개변수 타입은 생략
 *  람다를 언어 차원에서 지원하면서
 * 기존에는 적합하지 않았던 곳에서도 함수 객체를 실용적으로 사용할 수 있음
 * 예를 들어 람다를 이용하면 열거 타입의 인스턴스 필드에 람다를 저장해서
 * 상수별로 다르게 동작하는 코드를 쉽게 구현 가능
 * 메소드나 클래스와 달리, 람다는 이름이 없고 문서화도 못하므로
 * 코드 자체로 동작이 명확히 설명되지 않거나 코드 줄 수가 많아지면
 * 람다를 쓰지 말아야 함
 * 람다는 세 줄을 초과하면 가독성이 매우 나빠지기 때문에
 * 한 줄에서 길어야 세 줄 안에 끝날 때 사용
 *  람다는 함수형 인터페이스에서만 쓰임
 * 추상 클래스의 인스턴스를 만들 때 람다를 쓸 수 없으니
 * 익명 클래스를 사용해야 함
 * 추상 메소드가 여러 개인 인터페이스의 인스턴스를 만들 때도
 * 익명 클래스를 써야함
 * 람다는 자신을 참조할 수 없는데, 람다에서의 this는 바깥 인스턴스를 가리킴
 * 반면 익명 클래스의 this는 익명 클래스 인스턴스 자신을 가리키므로
 * 자신을 참조해야 한다면 익명 클래스를 써야함
 *  람다도 익명 클래스처럼 직렬화 형태가 구현별로 다를 수 있으므로,
 * 람다를 직렬화하지 말아야 하고,
 * 직렬화해야하는 함수 객체가 있다면
 * private 정적 중첩 클래스의 인스턴스를 사용
 *
 */
public class item42 {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();

        // 42-1 익명 클래스 인스턴스를 함수 객체로 사용
        Collections.sort(words, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });

        // 42-2 람다식을 함수 객체로 사용
        Collections.sort(words,
                (s1, s2) -> Integer.compare(s1.length(), s2.length()));

        // 비교자 생성 메소드 사용
        Collections.sort(words, Comparator.comparingInt(String::length));

        // List 인터페이스 sort 메소드 사용
        words.sort(Comparator.comparingInt(String::length));
    }
}

// 42-4 람다를 인스턴스 필드에 저장해 상수별 동작을 구현
enum Operation {
    PLUS("+", (x, y) -> x + y),
    MINUS("-", (x, y) -> x - y),
    TIMES("*", (x, y) -> x * y),
    DIVIDE("/", (x, y) -> x / y);

    private final String symbol;
    private final DoubleBinaryOperator op;

    Operation(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);
    }
}