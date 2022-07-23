package ch2.item3;

/*
 * private 생성자나 열거 타입으로 싱글턴임을 보증하라
 * 
 * singleton : 인스턴스를 하나만 생성할 수 있는 클래스
 */
public class Item3 {

    public static void main(String[] args) {

    }

}

/*
 * 싱글턴 만드는 방식 1
 * private 생성자 + public static final 인스턴스
 *
 * 장점
 * 1. 싱글턴임이 명백
 *    다른 객체를 참조할 수 없음
 * 2. 간결함
 *
 * 단점
 * 1. 리플렉션 API로 private 생성자 호출 가능
 *    -> 두 번째 객체 생성 시도 시 예외
 */
class Singleton1 {
    public static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {}
}

/*
 * 싱글턴 만드는 방식 2
 * private 생성자 + private static final 인스턴스
 * + public static 정적 팩토리 메소드
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
 * 원소 하나인 열거 타입
 *
 * 장점
 * 1. 간결
 * 2. 직렬화 쉬움
 * 3. 복잡한 직렬화, 리플렉션 공격에서 추가 인스턴스 생성 막음
 *
 * 단점
 * 1. Enum 외의 클래스 상속 시 사용 불가
 */
enum Singleton3 {
    INSTANCE;
}