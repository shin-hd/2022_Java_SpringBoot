package ch8.item49;

import java.math.BigInteger;
import java.util.Objects;

/*
 * item49. 매개변수가 유효한지 검사하라
 *
 *  메소드와 생성자가 입력 매개변수 값이 특정 조건을 만족하는지 검사하는 것은
 * "오류를 가능한 한 빨리 잡아야 한다"는 일반 원칙의 한 사례
 *
 *  매개변수 검사를 제대로 하지 않으면 발생할 수 있는 문제
 * 1. 메소드가 수행되는 중간에 모호한 예외를 던지며 실패할 수 있음
 * 2. 메소드가 문제없이 수행되더라도 객체를 이상하게 만들어서
 *    이후 알 수 없는 시점에 이 메소드와 관련 없는 오류 발생
 *      => 원인파악 힘듦
 *
 *  public과 protected 메소드는 매개변수 값이 잘못됐을 때
 * 던지는 예외를 문서화해야 함
 * API 사용가가 제약을 지킬 가능성을 높여줌
 * 클래스의 모든 public 메소드에 공통으로 적용되는 설명은
 * 클래스 수준 주석을 사용하면 훨씬 깔끔함
 * 
 *  java.util.Objects.requireNonNull 메소드를 사용하면
 * null 검사를 수동으로 하지 않아도 됨
 *  범위 검사 기능도 더해진 checkFormIndexSize, checkFromToIndex,
 * checkIndex 메소드들도 추가되었는데, null 검사 메소드만큼 유연하진 않음
 *
 *  public이 아닌 메소드라면 단언문(assert)를 사용해 매개변수 유효성을 검증할 수 있음
 * 핵심은 단언문들이 단언한 조건이 무조건 참이라고 선언한다는 것
 * 단언문과 일반적 유효성 검사의 차이
 * 1. 실패하면 AssertionError를 던짐
 * 2. 런타임에 아무 효과도, 아무 성능 저하도 없음
 *
 *  메소드가 직접 사용하지는 않지만 나중에 쓰기 위해 저장하는 매개변수는
 * 특히 신경써야 함
 * 만약 매개변수 검사를 안한다면 나중에 사용할 때서야 예외가 발생해서
 * 추적이 어려움
 * 생성자 매개변수의 유효성 검사는 클래스 불변식을 어기는 객체가
 * 만들어지지 않도록 하는 데 꼭 필요함
 *
 *  유효성 검사 비용이 높거나 계산 과정에서 암묵적으로 검사가 수행된다면
 * 유효성 검사를 안하는게 나음
 * ex) 리스트의 원소들이 상호비교가 성립하는지 일일히 확인 불가
 *
 *  메소드는 최대한 범용적으로 설계해야 하기 때문에
 * 매개변수 제약은 최소한으로 해야 함
 *
 */
public class item49 {
    private String strategy;

    // 49-1 자바의 null 체크 기능
    item49(String strategy) {
        this.strategy = Objects.requireNonNull(strategy, "전략");
    }
    
    // 49-2
    private static void sort(long a[], int offset, int length) {
        assert a != null;
        assert offset >= 0 && offset <= a.length;
        assert length >= 0 && length <= a.length - offset;
        // 계산 수행
    }
    

    public static void main(String[] args) {
    }

    /**
     * (현재 값 mod m) 값을 반환한다. 이 메소드는
     * 항상 음이 아닌 BigInteger를 반환한다는 점에서 remainder 메소드와 다르다.
     *
     * @param m 계수(양수여야 한다.)
     * @return 현재 값 mod m
     * @throws ArithmeticException m이 0보다 작거나 같으면 발생한다.
     */
    public BigInteger mod(BigInteger m) {
        if (m.signum() <= 0)
            throw new ArithmeticException("계수 (m)는 양수여야 합니다. " + m);
        // 계산 수행
        return m;
    }
}
