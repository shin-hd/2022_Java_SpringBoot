package ch2.item1;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/*
 * 생성자 대신 정적 팩터리 메서드를 고려하라
 *
 * 장점
 * 1. 이름을 가질 수 있음
 * 2. 인스턴스를 새로 생성하지 않아도 됨
 * 3. 반환 타입의 하위 타입 객체 반환 가능
 *    인터페이스의 정적 팩토리 메소드로 객체 생성
 * 4. 매개변수에 따라 다른 클래스의 객체 반환 가능
 * 5. 정적 팩토리 메소드 작성 시점에서
 *    반환 객체 클래스가 없어도 상관없음
 *
 * 단점
 * 1. 상속 시 public, protected 생성자가 필요
 *    정적 팩터리 메소드만 제공 시 하위 클래스 생성 불가
 * 2. 프로그래머가 찾기 어려움 (생성자는 명확함)
 *    -> 네이밍 규약 따라 사용 (from, of, valueOf, instance, create, getT, newT, T 등)
 * 3.
 */
public class Item1 {

    public static void main(String[] args) {
        String str1 = "123";
        String str2 = String.valueOf(123);

        User user1 = new User();
        User user2 = User.defaultUser();
        User user3 = User.user("김철수", 25);
        User user4 = User.admin("박민수", 35);

        String categoryId = "1a2b3c";
        String categoryName = "식품";
        Category category1 = Category.valueOf(categoryId, categoryName);
        Category category2 = Category.valueOf(categoryId, categoryName);

        System.out.println(str1.equals(str2));
        System.out.println(user1.equals(user2));
        System.out.println(user3.getRole() + " " + user4.getRole());
        System.out.println(category1.equals(category2));
    }

}

class User {
    private String name;
    private int age;
    private final String role;

    User() {
        this.name = "홍길동";
        this.age = 20;
        this.role = "USER";
    }

    User(String name, int age, String role) {
        this.name = name;
        this.age = age;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static User defaultUser() {
        return new User();
    }
    public static User user(String name, int age) { return new User(name, age, "USER"); }
    public static User admin(String name, int age) { return new User(name, age, "ADMIN"); }


}

class Category {

    private final String id;
    private final String name;
    private static List<Category> categories = new ArrayList<Category>();

    private Category(String id, String name) {
        this.id = id;
        this.name = name;
        categories.add(this);
    }

    public static Category valueOf(String id, String name) {
        Category myCategory = categories.stream().filter((category) -> category.id == id)
                .findFirst().orElse(null);
        return myCategory != null ? myCategory : new Category(id, name);
    }

    public static Boolean removeById(String id) {
        Category removedCategory = categories.stream().filter((category) -> category.id == id)
                .findFirst().orElse(null);

        return categories.remove(removedCategory);
    }

}