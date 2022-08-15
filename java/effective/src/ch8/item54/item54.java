package ch8.item54;

/*
 * item54. null이 아닌, 빈 컬렉션이나 배열을 반환하라
 *
 *  빈 컬렉션이나 배열이 아닌 null을 반환했을 때의 문제점은
 * 클라이언트에서 null을 반환한 상황을 처리하는
 * 방어 코드를 추가로 작성해야 해서 코드가 복잡해짐
 *
 *  빈 컨테이너 할당 비용에 대한 반박
 * 1. 성능 분석 결과 빈 컨테이너 할당에 들어가는 성능 차이는 미미
 * 2. 빈 컬렉션과 배열은 굳이 새로 할당하지 않아도 반환 가능
 *
 *  사용 패턴에 따라 빈 컬렉션 할당이 성능을 떨어뜨릴 수 있는데,
 * 매번 똑같은 빈 불변 컬렉션을 반환하도록 해서 해결 가능
 *
 *  배열은 단순하게 정확한 길이의 배열을 반환하면
 * 반환 배열 길이가 0일 수 있으므로 문제 없음
 * 성능이 신경쓰인다면 길이 0짜리 배열은 모두 불변이므로
 * 미리 만들어놓고 매번 그 배열을 반환하면 됨
 * 
 * 참고
 * List.toArray 메소드는 주어진 배열이 충분히 크면
 * 배열 안에 원소를 담아 반환,
 * 그렇지 않으면 새로 만들어 반환
 * 따라서 배열을 미리 만들어 넘길 필요가 없음
 */
public class item54 {
}