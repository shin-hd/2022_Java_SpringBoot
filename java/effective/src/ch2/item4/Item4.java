package ch2.item4;

/*
 * abstract class로는 인스턴스화를 막을 수 없음
 * 하위 클래스를 만들어 인스턴스화
 * -> private 생성자로 인스턴스 생성 방지
 *    + 상속 불가능
 */
public class Item4 {
    // 기본 생성자 만들어지는 것을 방지
    private Item4() {
        // 에러 던지기 필수는 아님
        // 실수로라도 생성자 호출하지 않도록 해줌
        throw new AssertionError();
    }
}
