package ch4.item22;

/*
 * 인터페이스는 타입을 정의하는 용도로만 사용하라
 *
 *  상수 인터페이스 안티패턴은 인터페이스를 잘못 사용한 예
 * @ 상수 인터페이스는 메소드 없이, static final 상수 필드로만 가득 찬 인터페이스
 * 클래스 내부에서 사용하는 상수는 내부 구현에 해당하고
 * 따라서 상수 인터페이스를 구현하는 것은
 * 내부 구현 클래스의 API로 노출하는 행위임
 * 클래스가 어떤 상수 인터페이스를 사용하든 사용자에게는 의미 없고
 * 오히려 혼란을 주며 클라이언트 코드가 상수들에 종속됨
 * final이 아닌 클래스가 상수 인터페이스를 구현하면
 * 모든 하위 클래스의 네임스페이스가 오염됨
 *  특정 클래스나 인터페이스와 연관된 상수라면
 * 클래스나 인터페이스 자체에 추가해야 함
 * 열거 타입에 적합한 상수라면 열거 타입으로 공개하거나,
 * 인스턴스화 불가능한 유틸리티 클래스에 담아 공개
 * (private {} 생성자로 인스턴스화 방지)
 * 유틸리티 클래스에 정의된 상수를 클라이언트에서 사용하려면
 * 클래스 이름까지 명시해야 함
 * ex) PhysicalConstants.AVOGADROS_NUMBER
 *
 *
 */
public class item22 {
}
