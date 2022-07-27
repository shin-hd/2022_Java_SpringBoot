package ch4.item19;

import java.time.Instant;

/*
 * 상속을 고려해 설계하고 문서화하라. 그렇지 않았다면 상속을 금지하라
 *
 * # 상속을 고려한 설계와 문서화
 * 1. 상속용 클래스는 재정의할 수 있는 메소드를
 *    내부적으로 어떻게 이용하는지 문서로 남겨야 함
 * final이 아닌 public, protected 메소드의 호풀 순서, 영향 등등
 * 메소드 주석에 @implSpec 태그를 붙이면
 * API 문서 메소드 설명 끝에 메소드 내부 동작 방식을 설명하는 문서 생성
 * 2. 클래스의 내부 동작 과정 중간에 끼어들 수 있는 훅을 잘 선별하여
 *    protected 메소드 형태로 공개해야 할 수도 있음
 * 최종 사용자는 protected 메소드에 관심이 없지만,
 * 하위 클래스에서 구현하는 경우에는 도움됨
 * 3. 상속용 클래스를 시험하는 방법은 직접 하위 클래스를 만들어보는 것이 유일
 *    상속용 클래스는 배포 전에 반드시 하위 클래스를 만들어 검증
 * 하위 클래스 3개 이상, 최소 하나는 제 3자가 작성해봐야
 * 필요한 protected 멤버인지, 쓰이지 않아 private으로 해야 하는지 알 수 있음
 * 4. 상속용 클래스의 생성자는 재정의 가능 메소드를 호출해서는 안됨
 * 상위 클래스 생성자가 하위 클래스 생성자보다 먼저 실행되므로
 * 하위 크래스에서 재정의한 메소드가 하위 클래스 생성자보다 먼저 실행되므로
 * 하위 클래스 생성자가 초기화하는 값에 메소드가 의존한다면 의도대로 동작 X
 * 상위 클래스 생성자 > 하위 클래스 재정의 메소드 > 하위 클래스 생성자
 * clone, readObject 메소드도 생성자와 비슷한 효과를 내므로
 * 재정의 가능 메소드를 호출하면 안됨
 * 5. Serializable을 구현한 상속용 클래스가
 * readResolve나 write 메소드를 갖는다면 protected로 선언해야 함
 *
 * 클래스를 상속용으로 설계하려면 많은 노력이 들고 제약도 상당함
 * 그럼 일반 구체 클레스는?
 * 구체 클래스를 내부만 수정해도 확장 클래스에서 문제가 발생하는 경우가 종종 있음
 * -> 가장 좋은 방법은 상속용으로 설계된 클래스가 아니라면 상속 금지
 * 상속을 금지하는 두 가지 방법(item17)
 * 1. 클래스를 final로 선언
 * 2. 생성자 private, package-private
 *    public 정적 팩토리
 * 인터페이스 구현, 래퍼 클래스 패턴 사용 등등
 * 상속 대신 사용 가능한 대안
 *
 * 표준 인터페이스를 구현하지 않은 구체 클래스라도
 * 상속을 허용해야 한다면 문서화
 *
 * 
 *
 */
public class item19 {
    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }
}
class Super {
    public Super() {
        overrideMe();
    }
    public void overrideMe() {}
}
final class Sub extends Super {
    private final Instant instant;
    Sub() {
        instant = Instant.now();
    }
    @Override public void overrideMe() {
        System.out.println(instant);
    }
}