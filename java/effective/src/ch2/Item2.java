package ch2;

public class Item2 {

    /*
    * 생성자에 매개변수가 많다면 빌더를 고려하라
    */
    public static void main(String[] args) {
        BuilderUser user = BuilderUser.builder()
                .name("hong-gildong")
                .email("test@gmail.com")
                .phone("010-1234-5678")
                .age(11)
                .build();

        System.out.println(user.toString());
    }
}

class BuilderUser {
    private final String name;
    private final String phone;
    private final String email;
    private int age;

    private BuilderUser(String name, String phone, String email, int age) {
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

        public BuilderUser.Builder name(String value) {
            name = value;
            return this;
        }

        public BuilderUser.Builder phone(String value) {
            phone = value;
            return this;
        }

        public BuilderUser.Builder email(String value) {
            email = value;
            return this;
        }

        public BuilderUser.Builder age(int value) {
            age = value;
            return this;
        }

        public BuilderUser build() {
            return new BuilderUser(name, phone, email, age);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String toString() {
        return "name: " + name + "\nemail: " + email + "\nphone: " + phone + "\nage: " + age;
    }
}