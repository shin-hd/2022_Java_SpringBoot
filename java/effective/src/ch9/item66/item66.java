package ch9.item66;

/*
 * item66. 네이티브 메소드는 신중히 사용하라
 *
 *  네이티브 메소드
 * : C나 C++같은 네이티브 프로그래밍 언어로 작성된 메소드
 *
 *  네이티브 메소드의 주된 쓰임
 * 1. 레지스트리같은 플랫폼 특화 기능
 * 2. 네이티브 코드로 작성된 기존 라이브러리
 * 3. 성능 개선을 목적으로 성능에 결정적 역할을 주는 영역만 따로 작성
 *
 *  성능을 개선할 목적으로 네이티브 메소드를 사용하는 것은 권장하지 않음
 * 그동안 JVM이 발전해서 지금의 자바는 다른 플랫폼에 견줄만한 성능을 보임
 *
 *  네이티브 라이브러리는 GMP를 필두로 개선 작업이 계속되어서
 * 고성능 다중 정밀 연산이 필요하다면 GMP를 사용하는 것을 고려해도 됨
 *
 *  네이티브 메소드의 단점
 * 1. 애플리케이션도 메모리 훼손 오류에서 안전하지 않음
 * 2. 플랫폼을 많이 타서 이식성도 낮음
 * 3. 디버깅도 더 어려움
 * 4. 주의하지 않으면 속도가 오히려 느려질 수 있음
 * 5. 자바 코드와 네이티브 코드 경계를 드나들 때마다 비용이 추가됨
 * 6. 네이티브 코드와 자바 콬드 사이에 접착 코드를 작성해야 하는데,
 *    귀찮고 가독성이 떨어짐
 * => 꼭 써야 할 때에만 사용
 *
 */
public class item66 {
}