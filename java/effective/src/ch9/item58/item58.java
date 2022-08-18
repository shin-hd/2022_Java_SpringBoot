package ch9.item58;

/*
 * item58. 전통적인 for 문보다는 for-each 문을 사용하라
 *
 *  전통적인 for 문의 단점
 * 1. 반복자와 인덱스 변수가 코드를 지저분하게 함
 * 2. 쓰는 요소 종류가 늘어나면 오류가 생길 가능성이 높아짐
 * 3. 잘못된 변수를 사용했을 때 컴파일러가 잡아준다는 보장이 없음
 * 4. 컬렉션이냐 배열이냐에 따라 코드 형태가 상당히 달라짐
 *
 *  for-each 문의 장점
 * 1. 반복자와 인덱스 변수를 사용하지 않아 코드가 깔끔해지고 오류가 날 일이 없음
 * 2. 관용구로 컬렉션과 배열을 모두 처리할 수 있어 컨테이너 종류를 신경쓰지 않아도 됨
 *
 *  for-each 문을 사용할 수 없는 상황
 * 1. 파괴적인 필터링
 *  컬렉션을 순회하면서 조건에 맞는 원소를 제거해야 하는 경우
 *  Collection의 removeIf 메소드(+람다)를 사용해서 컬렉션을 명시적으로 순회하는 일을 피할 수 있음
 * 2. 변형
 *  리스트나 배열을 순회하면서 값 일부 혹은 전체를 교체하는 경우
 * 3. 병렬 반복
 *  여러 컬렉션을 병렬로 순회히야 한다면 각각의 반복자와 인덱스 변수를 사용해
 *  엄격하고 명시적으로 제어해야 함
 *
 *  for-each 문은 컬렉션과 배열뿐만 아니라 Iterable 인터페이스를 구현한
 * 객체라면 무엇이든 순회할 수 있으므로, 원소 묶음을 표현하는 타입을 작성한다면
 * Collecton 인터페이스는 구현하지 않기로 했더라도
 * Iterable을 구현하는게 좋을 수 있음
 */
public class item58 {
}