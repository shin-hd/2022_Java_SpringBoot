package ch9.item67;

/*
 * item67. 최적화는 신중히 하라
 *
 *  섣부른 최적화는 빠르지 않고 제대로 동작하지 않으면서 수정하기 어려운
 * 소프트웨어를 탄생시킴
 *
 * 1. 빠른 프로그램보다는 좋은 프로그램을 작성해야 함
 *  좋은 프로그램은 개별 구성요소의 내부가 독립적이어서
 *  시스템의 나머지에 영향을 주지 않고도 각 요소를 재설계할 수 있음
 *   구현상 문제는 나중에 최적화 가능하지만,
 *  구조적 결함은 전체를 다시 작성해야 함
 * 2. 성능을 제한하는 설계를 피하는 게 좋음
 *  API, 프로토콜, 데이터 포맷 등의 설계 요소는
 *  완성 후에는 변경이 어렵거나 불가능함
 *  따라서 설계 요소가 성능을 저하시키면 해결이 힘듦
 * 3. API를 설계할 때 성능에 주는 영향을 고려해야 함
 *  public 타입을 가변으로 만들거나,
 *  컴포지션으로 구현 가능해도 상속을 사용하거나,
 *  인터페이스가 있는데 구현 타입을 사용하는 등
 *  설계를 잘못하면 개선된 수단이 나와도 적용이 불가능해질 수 있음
 * 4. 성능을 위해 API를 왜곡하는 것은 매우 안좋음
 *  만든 성능 문제는 나중에 해결될 수 있지만,
 *  API가 왜곡되면 영원히 고칠 수 없음
 *
 *  우선 신중하게 설계해서 좋은 구조를 갖춘 프로그램을 완성한 다음에,
 * 성능에 만족을 못한다면 그제서야 최적화를 고려할만 함
 *  최적화 규칙
 * 1. 하지마라
 * 2. 아직 하지 마라
 * 3. 최적화 시도 전후로 성능을 축정하라
 *  최적화 기법이 성능을 높이지 못하는 결우가 많고,
 *  심지어 더 나빠지게 할 때도 있음
 *
 *  프로파일링 도구는 최적화해야 할 위치를 찾는 데 도와주며
 * 시스템 규모가 커질수록 프로파일러가 중요해짐
 *
 *  자바는 프로그래머 코드와 CPU 명령 사이의 추상화 격차가 커서
 * 최적화 성능 예측이 어려움
 * 따라서 최적화 효과를 각 실행 환경에서 측정해야 함
 */
public class item67 {
}