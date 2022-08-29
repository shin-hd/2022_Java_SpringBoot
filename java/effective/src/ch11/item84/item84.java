package ch11.item84;

/*
 * item84. 프로그램의 동작을 스레드 스케줄러에 기대지 말라
 *
 *  정확성이나 성능이 스레드 스케줄러에 따라 달라지는 프로그램은
 * 다른 플랫폼에 이식하기 어려움
 *
 *  견고하고 빠릿하고 이식성 좋은 프로그램을 작성하는 가장 좋은 방법
 * : 실행 가능한 스레드의 평균적인 수를
 *   프로세서 수보다 지나치게 많아지지 않도록 하는 것
 * 전체 스레드 수 == 실행 가능 스레드 수가 아님
 *
 *  실행 가능한 스레드 수를 적게 유지하는 주요 기법
 * - 각 스레드가 작업을 완료한 후 일이 생길때까지 대기
 * - 작업을 적절한 크기로 적절한 수의 스레드들에게 분배
 *
 *  스레드가 피해야 하는 상태 : 바쁜 대기 상태
 * 일을 안하면서 스레드를 점유해서
 * 프로세서에 부담을 주고 다른 일이 실행되지 못하게 방해
 * ex) while(true) { synchronized(this) {...} }
 * 
 *  특정 스레드가 CPU 시간을 충분히 얻지 못해서 간신히 돌아가는 상황에서
 * - Thread.yield를 써서 고치지 말아야 함
 *  환경별로 성능이 들쭉날쭉이며 테스트 수단도 없기 때문
 * - 스레드 우선순위를 조절하지 말아야 함
 *  이식성이 떨어지고 진짜 원인을 찾기 전에는 계속 문제가 반복됨
 *
 */
public class item84 {
}
