package ch2.Item5;

/*
 * 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라
 */
public class Item5 {

    /* 정적 유틸리티를 잘못 사용한 예
     * - 유연하지 않고 테스트하기 어려움 (다른 타입 리소스 사용이라던가)
     * 
     * private static final User me = new User();
     */

    /* 싱글턴을 잘못 사용한 예
     * - 유연하지 않고 테스트하기 어려움 (다른 타입 리소스 사용이라던가)
     *
     * private final User me = new User();
     * public static Item5 INSTANCE = new Item5();
     */

    /*
     * 여러 자원을 사용할 수 있도록 만드는 방법
     * 
     * 필드에서 final 제거
     * - 오류를 내기 쉬움
     * - 멀티스레드 환경에서 사용 불가
     * - 사용 자원에 따라 동작이 달라지는 클래스에 적용 불가
     * 
     * => 클래스가 여러 리소스 인스턴스 지원해야 함
     *   : 의존 객체 주입(인스턴스 생성 시 생성자에 자원을 넘겨주는 방식)
     * 
     * 장점
     * 1. 단순하다
     * 2. 자원이 몇 개든 의존 관계가 어떻든 잘 동작
     * 3. 불변을 보장해서 여러 클라이언트가 안심하고 공유 가능
     * 4. 생성자, 정적 팩토리, 빌더 모두에 응용 가능
     * 
     * 단점
     * 1. 의존성이 수천 개나 되는 큰 프로젝트에서 코드를 어지럽게함
     *    -> 대거, 주스, 스프링 등 프레임워크 사용 시 헤소가능
     *
     * 변형
     * 생성자에 자원 팩토리 넘겨주기
     */

    private final User me;

    public Item5(User me) {
        this.me = me;
    }

}

class User {
    String name;
    String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}