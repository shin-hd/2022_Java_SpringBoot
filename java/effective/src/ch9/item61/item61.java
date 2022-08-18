package ch9.item61;

/*
 * item61. 박싱된 기본 타입보다는 기본 타입을 사용하라
 *
 *  자바 데이터 타입
 * 1. int, double, boolean 같은 기본타입
 * 2. String, List 같은 참조 타입
 * 
 *  박싱된 기본 타입 : 기본 타입에 대응하는 참조 타입
 *
 *  기본 타입과 박싱된 기본 타입의 차이
 * 1. 기본 타입은 값만 가지고 있으나, 박싱된 기본 타입은 식별성이라는 속성도 가짐
 *    => 같은 값이라도 다르다고 식별될 수 있음
 * 2. 기본 타입 값은 언제나 유효하지만, 박싱된 기본 타입은 null을 가질 수 있음
 * 3. 기본 타입이 시간과 메모리 사용면에서 더 효율적임
 *
 *  박싱된 기본 타입을 사용할 때 발생할 수 있는 문제
 * 1. == 연산자를 사용하면 오류가 일어남
 *  == 연산자가 '객체 참조'의 식별성을 검사해서
 *  값이 같더라도 false가 될 수 있음
 *  => 비교자 생성 메소드나 정적 compare 메소드를 사용해야 함
 *     아니면 기본 타입으로 언박싱해서 비교
 * 2. null 참조를 언박싱하면 NullPointerException이 발생
 *    => 기본 타입으로 사용하면 해결
 * 3. 방싱, 언박싱의 반복으로 성능이 체감될 정도로 저하됨
 *
 *  박싱된 기본 타입을 사용해야하는 경우
 * 1. 컬렉션의 원소, 키, 값에 사용
 *  자바 언어가 타입 매배견수로 기본 타입을 지원하지 않으므로
 *  타입 매개변수로는 박싱된 기본 타입을 써야 함
 * 2. 리플렉션을 통해 메소드를 호출할 때 사용
 *
 */
public class item61 {
}