package ch11.item82;

/*
 * item82. 스레드 안전성 수준을 문서화하라
 *
 *  API 문서에서 여러 스레드가 호출했을 때의 동작을 문서화해야
 * 클래스 사용자가 적절하게 동기화할 수 있어 오류가 없음
 *
 *  자바독 기본 옵션에서 생성한 API 문서는 synchronized 한정자가 포함되지 않음
 * synchronized 한정자를 선언할지 말지는 구현 이슈라서,
 * 메소드가 스레드 안전하지 아닌지 판단할 근거가 될 수 없음
 *
 *  멀티스레드 환경에서 API를 안전하게 사용하게 하려면
 * 스레드 안전성 수준을 명확히 명시해야 함
 *
 *  스레드 안전성 수준 (높은 순)
 * - 불변(immutable)
 *  외부 동기화도 필요 없음
 *  ex) String, Long, BigInteger
 * - 무조건적 스레드 안전
 *  인스턴스가 수정될 수 있지만, 내부에서 충실히 동기화해서
 *  외부 동기화 없이 동시에 사용해도 안전
 *  ex) AtomicLong, ConcurrentHashMap
 * - 조건부 스레드 안전
 *  무조건적 스레드 안전과 같지만,
 *  일부 메소드는 동시에 사용하려면 외부 동기화가 필요
 *  ex) Collections.synchronized 래퍼 메소드가 반환하는 컬렉션
 * - 스레드 안전하지 않음
 *  인스턴스가 수정될 수 있으며,
 *  동시에 사용하려면 메소드 호출을 외부 동기화 매커니즘으로 감싸야 함
 *  ex) ArrayList, HashMap 등 기본 컬렉션
 * - 스레드 적대적
 *  모든 메소드 호출을 외부 동기화로 감싸더라도 멀티스레드 환경에서 안전하지 않음
 *  일반적으로 정적 데이터를 아무 동기화 없이 수정함
 *  스레드 적대적인 클래스나 메소드는
 *  보통 고쳐서 재배포하거나 사용 자제 API로 지정됨
 *
 *  조건부 스레드 안전 클래스는 주위해서 문서화해야 함
 * - 어떤 순서로 호출할 때 외부 동기화가 필요한지
 * - 어떤 락을 얻어야 하는지
 *
 *  클래스가 외부에서 사용가능한 락 제공
 * => 클라이언트에서 메소드 호출을 원자적으로 수행 가능
 * 하지만 내부에서 처리하는 고성능 동시성 제어 메커니즘과 혼용할 수 없고
 * 공개된 락을 오래 쥐고 놓지 않는 서비스 거부 공격을 받을 수 있음
 *
 *  서비스 거부 공격을 마긍려면
 * synchronized 대신 비공개 락 객체를 사용해야 함
 * 
 *  비공개 락 객체
 * 클래스 바깥에서 볼 수 없어 클라이언트가 객체의 동기화에 과녕할 수 없음
 * 무조건적 스레드 안전 클래스에서만 사용 가능
 *
 */
public class item82 {
    /* 코드 82-1. 비공개 락 객체 관용구
    private final Object lock = new Object();

    public void foo() {
        synchronized(lock) {
            ...
        }
    }
     */
}
