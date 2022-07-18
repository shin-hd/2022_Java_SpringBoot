package ch2.Item5;

/*
 * 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라
 *
 *
 */
public class Item5 {

    /* 정적 유틸리티를 잘못 사용한 예
     *
     * private static final User me = new User();
     */

    /* 싱글턴을 잘못 사용한 예
     *
     * private final User me = new User();
     * public static Item5 INSTANCE = new Item5();
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