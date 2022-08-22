package ch10.item71;

/*
 * item71. 필요 없는 검사 예외 사용은 피하라
 *
 *  검사 예외를 제대로 활용하면 API와 프로그램의 질을 높일 수 있음
 * 발생한 문제를 프로그래머가 처리해서 안정성을 높이지만,
 * API 사용자에게 부담을 줄 수 있음
 *  
 *  메소드가 단 하나의 검사 예외를 던지는 경우
 * 그 예외 하나때문에 try 블록을 추가해야 하므로 부담이 큼
 * => 예외를 안 던지는 방법 고려
 * 
 *  검사 예외를 회피하는 방법
 * 1. 결과 타입을 담은 옵셔널을 반환하는 것
 * 단점 : 예외가 발생한 이유를 알려주는 부가 정보를 담을 수 없음
 * 2. 검사 예외를 던지는 메소드를 쪼개 비검사 예외로 바꾸기
 *  모든 상황에 적용할 수는 없지만 적용가능하다면 더 편한 API를 제공할 수 있음
 *  코드가 더 이쁘진 않지만 더 유연한 것은 확실
 *  하지만 스레드가 동시에 접근할 수 있거나 외부 요인에 의해
 * 상태가 변할 수 있다면 사용할 수 없고,
 *  상태 검사 메소드가 작업 일부를 중복 수행한다면
 * 성능에서 손해이기 때문에 마찬가지로 사용이 부적절할 수 있음
 *
 */
public class item71 {
}
