package ch6.item41;

/*
 * item41. 정의하려는 것이 타입이라면 마커 인터페이스를 사용하라
 *
 *  아무 메소드도 담고 있지 않고, 단지 자신을 구현하는 클래스가
 * 특정 속성을 가짐을 표시해주는 인터페이스 : 마커 인터페이스
 *  마커 어노테이션이 등장했지만 마커 인터페이스도 장점이 있음
 * 1. 마커 인터페이스는 이를 구현한 클래스의 인스턴스를 구분하는 타입으로 쓸 수 있지만,
 * 마커 어노테이션은 그렇지 않음
 * 인터페이스는 엄연히 타입이므로 객체가 이를 구현했는지를
 * 컴파일타임에서 알 수 있는데, 어노테이션은 런타임에서야 알 수 있음
 * 2. 적용 대상을 더 정밀하게 지정할 수 있음
 * 특정 인터페이스를 구현한 클래스에만 마커를 적용하고 싶다면
 * 이를 인터페이스로 정의했을 때는 그냥 해당 클래스가 인터페이스를
 * 구현하면 됨
 *  Set 인터페이스처럼 특정 인터페이스의 하위 타입에만 적용할 수 있으며,
 * 아무 규약에도 손대지 않은 마커 인터페이스는 충분히 있을 수 있음
 * 이런 마커 인터페이스는 객체 특정 부분을 불변식으로 규정하거나,
 * 그 타입의 인스턴스는 다른 클래스의 특정 메소드가 처리할 수 있다는
 * 사실을 명시하는 요도로 사용할 수 있을 것임
 *  반대로 마커 어노테이션이 마커 인터페이스보다 나은 점으로는
 * 어노테이션 시스템의 지원을 받는다는 점을 들 수 있음
 * 따라서 어노테이션을 적극 활용하는 프레임워크에서는
 * 마커 어노테이션을 사용하는 것이 일관성을 지키는 데 유리함
 *  클래스와 인터페이스 외의 프로그램 요소에 마킹해야 할 때는
 * 마커 어노테이션을 사용해야 하는데,
 * 인터페이스를 구현하거나 확장하려면 클래스나 인터페이스이어야 하기 때문
 * 마커를 클래스나 인터페이스에 적용해야 한다면
 * 마킹된 객체를 매개변수로 받는 메소드를 작성할 일이 있다면 인터페이스를,
 * 없다면 어노테이션을 사용하면 됨
 *
 * 타입 정의 -> 인터페이스
 * 타입 정의 x -> 인터페이스 x
 */
public class item41 {
}