package ch4.item18;

/*
 * 상속보다는 컴포지션을 사용하라
 *
 * # 메소드 호출과 달리 상속은 캡슐화를 깨뜨림
 * 상위 클래스는 릴리스마다 내부 구현이 달라질 수 있으며,
 * 그 여파로 하위 클래스가 오동작할 수 있음
 * ex)
 * 클래스 재정의 문제
 *  상위 클래스 함수의 내부구현 모르고 사용
 *  다시 구현하는 방식은 어렵고, 시간 들고, 오류 성능 위험, private이라면 구현 불가능
 * 메소드 추가 문제
 *  상위 클래스 새 메소드가 추가한 메소드와 시그니처가 같고 반환 타입이 다르면 컴파일 불가
 *  반환 타입도 같으면 재정의한 것
 *  상위 클래스 메소드가 요구하는 규약 만족 x
 * => 기존 클래스를 확장하는 대신, 새로운 클래스를 만들고 private으로 기존 클래스 인스턴스 참조
 *    : 컴포지션
 * 다른 인스턴스를 감싸고 있는 클래스 : 래퍼 클래스
 * 다른 클래스에 기능을 덧씌움 : 데코레이터 패턴
 * 래퍼 객체가 내부 객체에 자신의 참조를 넘김 : 위임
 *
 * # 래퍼 클래스의 단점
 * 래퍼 클래스가 콜백 프레임워크와는 어울리지 않음
 * 콜백 프레임워크에서는 자신의 참조를 다른 객체에 넘겨 다음 콜백 때 사용하도록 함
 * 내부 객체는 래퍼의 존재를 몰라서 this를 넘기고, 콜백은 내부 객체를 호출
 * : SELF 문제
 * 재사용할 수 있는 전달 클래스를 인터페이스마다 만들어두면
 * 전달 클래스들을 쉽게 구현 가능
 *
 * # 상속은 반드시 하위 클래스가 상위 클래스의 '진짜' 하위 타입인 경우에만 사용
 * 컴포지션을 써야 할 상황에서 상속을 사용하면
 * 내부 구현을 불필요하게 노출하는 꼴
 * => 많은 문제 야기
 *
 * 상속은 상위 클래스의 결함까지도 승계
 */
public class item18 {
}