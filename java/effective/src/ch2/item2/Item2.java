package ch2.item2;

/*
 * 생성자에 매개변수가 많다면 빌더를 고려하라
 *
 * 장점
 * 빌더 자신을 반환해서 연쇄적 호출 가능
 * 계층적으로 설계된 클래스와 함꼐 쓰기 좋음
 *
 * vs 점층적 생성자 패턴(overload된 생성자)
 * 매개변수가 많아지면 클라이언트 코드를 작성하거나 읽기 어려움
 * 실수하기 쉬움
 *
 * vs 자바빈즈 패턴(세터 사용)
 * 객체를 세터로 완전히 만들기 전까지 일관성이 무너진 상태
 * 불변 불가능(세터)
 *
 */
public class Item2 {

    public static void main(String[] args) {
        User user = User.builder()
                .name("hong-gildong")
                .email("test@gmail.com")
                .phone("010-1234-5678")
                .age(11)
                .build();

        System.out.println(user.toString());
    }
}

class User {
    private final String name;
    private final String phone;
    private final String email;
    private int age;

    private User(String name, String phone, String email, int age) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }

    public static class Builder {
        private String name;
        private String phone;
        private String email;
        private int age = 0;

        public User.Builder name(String value) {
            name = value;
            return this;
        }

        public User.Builder phone(String value) {
            phone = value;
            return this;
        }

        public User.Builder email(String value) {
            email = value;
            return this;
        }

        public User.Builder age(int value) {
            age = value;
            return this;
        }

        public User build() {
            return new User(name, phone, email, age);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String toString() {
        return "name: " + name + "\nemail: " + email + "\nphone: " + phone + "\nage: " + age;
    }
}