package ch6.item40;

/*
 * item40. @Override 어노테이션을 일관되게 사용하라
 *
 *  @Override는 메소드 선언에만 달 수 있으며,
 * 이 어노테이션을 달았다는 것은 상위 타입 메소드를 재정의했음을 의미
 * 이것을 일관되게 사용하면 여러 악명 높은 버그들을 예방해줌
 *  @Override를 달지 않으면 overriding을 하려고 한건지
 * overloading을 하려고 한건지 알 수 없음
 * 따라서 상위 클래스 메소드를 재정의하려는
 * 모든 메소드에 @Override 어노테이션을 달아야 함
 * 유일한 예외는 구체 클래스에서 상위 클래스의 추상 메소드를 재정의할 때 뿐
 * 물론 @Override를 붙이고 싶다면 붙여도 됨
 *  IDE는 @Override를 일관되게 사용하도록 도와줌
 * 설정을 활성화해놓으면 @Override가 달려있지 않은 메소드가
 * 재정의되었다고 경고
 *  클래스뿐만 아니라 인터페이스의 메소드를 재정의할 때도 사용 가능
 * 디폴트 메소드를 지원하기 시작하면서, 인터페이스 메소드를 구현한
 * 메소드에도 @Override를 다는 습관을 들이면
 * 시그니처가 올바른지 재차 확신할 수 있음
 * 구현할 인터페이스에 디폴트 메소드가 없다는 것을 안다면
 * 생략해서 코드를 깔끔하게 해도 상관 없음
 *  하지만 추상 클래스나 인터페이스에서는 상위 클래스나 상위 인터페이스의
 * 메소드를 재정의하는 모든 메소드에 @Override를 다는 것이 좋음
 */
public class item40 {
}
