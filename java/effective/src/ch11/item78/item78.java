package ch11.item78;

/*
 * item78. 공유 중인 가변 데이터는 동기화해 사용하라
 *
 *  synchronized 키워드는
 * 해당 메소드나 블록을 한번에 한 스레드씩 수행하도록 보장함
 *  동기화를 제대로 사용하면 어떤 메소드도
 * 객체의 상태가 일관적이지 않은 순간을 볼 수 없음
 * 또한 한 스레드가 만든 변화의 최종 결과를
 * 다른 스레드가 확인할 수 있게 해줌
 *
 *  언어 명세상 long과 double 외의 변수를 읽고 쓰는 동작은 원자적임
 *  그러나 스레드가 필드를 읽을 때 항상 수정이 완전히 '반영된' 값을 얻는 것을 보장하지만,
 * 한 스레드가 저장한 값이 다른 스레드에게 '보이는지'는 보장하지 않음
 * 따라서 동기화는 배타적 실행뿐 아니라 스레드 사이의 안정적인 통신에 꼭 필요함
 *
 *  Thread.stop 메소드는 안전하지 않아서 오래전에 사용 자제 API로 지정되었으므로
 * 절대 사용하지 말아야 함
 *
 *  다른 스레드를 멈추는 올바른 방법
 * 첫 번째 스레드는 자신의 boolean 필드를 폴링하면서 그 값이 true가 되면 멈추는데
 * 이 필드를 false로 초기화해놓고, 다른 스레드에서 이 스레드를 멈추려고 할 때 true로 변경하는 식
 *  boolean 필드를 읽고 쓰는 것은 원자적이라 동기화를 제거해도 된다고 생각할 수 있는데,
 * 동기화하지 않으면 메인 스레드가 수정한 값을 백그라운드 스레드가 언제 볼지 보증할 수 없음
 * 가상 머신 최적화 방식에 따라서 프로그램이 응답 불가 상태가 될 수 있음
 *  동기화는 쓰기와 읽기 모두가 동기화되어야 동작을 보장함
 * 
 *  반복문에서 속도가 더 빠른 대안으로,
 * boolean 필드를 volatile로 선언하면 동기화를 생략해도 됨
 * volatile 한정자는 항상 가장 최근에 기록된 값을 읽게 됨을 보장
 *
 *  volatile 주의점
 *  증가 연산자 ++는 코드상으로는 하나지만 실제로는 필드에 두 번 접근함
 * 스레드가 두 접근 사이에서 값을 읽어가면 잘못된 값을 읽게 됨
 * 이렇게 잘못된 결과를 계산해내는 오류 : 안전 실패
 * => volatile 말고 synchronized 한성자를 사용하면 해결
 *
 *  java.util.concurrent.atomic 패키지는 락 없이도 스레드 안전한
 * 프로그래밍을 지원하는 클래스들이 담겨있음
 * volatile은 동기화의 효과 중 통신 쪽만 지원하지만,
 * 이 패키지는 원자성까지 지원하고 성능도 동기화 버전보다 우수함
 *
 *  문제들을 피하는 가장 좋은 방법은 애초에 가변 데이터를 공유하지 않는 것
 * 가변 데이터는 단일 스레드에서만 사용하고
 * 문서에 남겨서 정책이 계속 지켜지도록 하는 것이 중요
 *
 *  스레드가 데이터를 수정하고 다른 스레드에 공유할 때 공유하는 부분만 동기화하면
 * 객체를 다시 수정할 일이 생기기 전까지 다른 스레드들이 자유롭게 값을 읽을 수 있음
 * 이런 객체 : 사실상 불변
 * 다른 스레드에 이런 객체를 건네는 행위 : 안전 발행
 * 
 *  객체를 안전하게 발행하는 방법
 * - 클래스 초기화 과정에서 객체를 정적 필드, volatile 필드,
 *   final 필드, 보통의 락을 통해 접근하는 필드, 동시성 컬렉션에 저장
 *
 */
public class item78 {
}