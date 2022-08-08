package ch7.item43;

/*
 * item43. 람다보다는 메소드 참조를 사용하라
 *
 *  람다가 익명 클래스보다 나은 점 중 가장 큰 특징은 간결함
 * 자바에는 함수 객체를 람다보다 간결히 만드는 방법이 있음
 * : 메소드 참조
 *  자바 8이 되면서 모든 기본 타입의 박싱 타입은
 * 여러 정적 메소드를 제공해서,
 * 람다 대신 이 메소드 참조를 전달함으로써
 * 같은 결과를 더 보기 좋게 얻을 수 있음
 * 하지만 어떤 람다에서는 매개변수 이름 자체가 가이드가 되어서
 * 람다를 유지하는 게 가독성과 유지보수에 유리할 수 있음
 *  람다로 할 수 없는 일이라면 메소드 참조로도 할수 없음
 * 그렇더라도 메소드 참조를 사용하는 편이 보통 더 짧고 간결하므로,
 * 너무 길거나 복잡한 람다의 좋은 대안이 됨
 *  IDE는 람다를 메소드 참조로 대체하라고 권하는데,
 * 항상 이를 따르는 것이 이득인 것은 아님
 * 메소드와 람다가 같은 클래스에 있다면
 * 람다가 메소드 참조보다 간결할 수 있음
 * ex) service.execute(GoshThisClassNameIsHumongous::action);
 *  => service.execute(() -> action());
 * ex) Function.identity() => (x -> x)
 *  메소드 참조의 유형은 다섯 가지가 있음
 * 1. 가장 흔한 유형은 정적 메소드를 가리키는 메소드 참조
 * ex) Integer::parseInt
 *     str -> Integer.parseInt(str)
 *  인스턴스 메소드를 참조하는 유형 두 가지
 * 2. 수신 객체를 특정하는 한정적 인스턴스 메소드 참조
 * 정적 참조와 비슷
 * 즉 함수 객체가 받는 인수와 참조되는 메소드가 받는 인수가 똑같음
 * ex) Instant.now()::isAfter
 *     Instant then = Instant.now(); t -> then.isAfter(t)
 * 3. 수신 객체를 특정하지 않는 비한정적 인스턴스 메소드 참조
 * 함수 객체를 적용하는 시점에 수신 객체를 알려줌
 * 이를 위해 수신 객체 전달용 매개변수가 매개변수 목록 첫 번째로 추가
 * ex) String::toLowerCase
 *     str -> str.toLowerCase()
 *  4. 클래스 생성자를 가리키는 메소드 참조
 * ex) TreeMap<K, V>::new
 *     () -> new TreeMap<K, V>()
 * 5. 배열 생성자를 가리키는 메소드 참조
 * ex) int[]::new
 *     len -> new int[len]
 *
 */
public class item43 {
}
