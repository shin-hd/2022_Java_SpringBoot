package ch2;

import java.util.function.Supplier;

public class Item3 {

    /*
     * private 생성자나 열거 타입으로 싱글턴임을 보증하라
     *
     */
    public static void main(String[] args) {

    }



}

/*
 * 싱글턴 만드는 방식 1
 *
 * 장점
 * 1. 싱글턴임이 명백
 *    다른 객체를 참조할 수 없음
 * 2. 간결함
 */
class Singleton1 {
    public static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {}
}

/*
 * 싱글턴 만드는 방식 2
 * 
 * 장점
 * 1. 싱글턴 아니게 바꾸기 쉬움
 * 2. 정적 팩토리를 제네릭 싱글턴 팩토리로 만들기 가능
 * 3. 정적 팩토리 메소드 참조자(getInstance)를 공급자(Supplier<T>)로 사용 가능
 */
class Singleton2 {
    private static final Singleton2 INSTANCE = new Singleton2();
    private Singleton2() {}
    public static Singleton2 getInstance() { return INSTANCE; }
    
    // 싱글턴임을 보장해주는 readResolve 메소드
    private Object readResolve() {
        return INSTANCE;
    }
}

/*
 * 싱글턴 만드는 방식 3
 *
 * 1. 간결
 * 2. 직렬화 쉬움
 * 3. 복잡한 직렬화, 리플렉션 공격에서 추가 인스턴스 생성 막음
 */
enum Singleton3 {
    INSTANCE;
}