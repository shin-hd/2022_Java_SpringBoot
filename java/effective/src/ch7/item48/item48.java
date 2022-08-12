package ch7.item48;

import java.math.BigInteger;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/*
 * item48. 스트림 병렬화는 주의해서 적용하라
 *
 *  동시성 프로그래밍을 할 때는 안전성과 응답 가능 상태를 유지하기 위해
 * 애써야하는데, 병렬 스트림 파이프라인 프로그래밍에서도 같은 노력이 필요함
 *  단순히 속도를 위해 무작정 스트림 파이프라인 parallel()을 호출한다면
 * CPU를 무지막지하게 잡아먹으면서 아무 결과를 내지 않을 수 있음
 *  원인은 스트림 라이브러리가 파이프라인을 병령화하는 방법을 찾지 못했기 때문
 * 데이터 소스가 Stream.iterate거나 중간 연산으로 limit을 쓰면
 * 파이프라인 병렬화로는 성능 개선을 기대할 수 없음
 * (limit를 다룰 때 CPU 여유가 되면 limit 마지막 원소를 포함한
 * 추가 원소를 처리 시도해서, 추가 연산들이 시간이 많이 걸린다면 그만큼 오버헤드)
 * 따라서 생각없이 스트림 파이프라인을 병렬화하면 안 됨
 *
 *  스트림 소스가 ArrayList, HashMap, HashSet, ConcurrentHashMap
 * 의 인스턴스거나 배열, int 범위, long 범위일 때 병렬화 효과가 좋음
 * 이유
 * 1. 데이터를 원하는 크기로 정확하고 쉽게 나눌 수 있어서
 *    여러 스레드에 분배하기 좋음
 * 2. 원소들을 순차적으로 실행할 때의 참조 지역성(locality)이 뛰어남
 *
 *  병렬화에 적합한 종단 연산
 * 1. 축소(reduction)
 *    Stream의 reduce 메소드, min, max, count, sum 메소드 등
 *    파이프라인에서 만들어진 모든 원소를 하나로 합치는 작업
 * 2. anyMatch, allMatch, noneMatch처럼 조건에 맞으면 바로 반환하는 메소드
 * 
 *  병렬화에 적합하지 않은 종단 연산
 * 1. 가변 축소(mutable reduction)를 수행하는
 *    Stream의 collect 메소드
 *
 *  스트림을 잘못 병렬화하면 성능이 나빠지고
 * 결과도 잘못되거나 예측불가능한 동작이 발생 <: 안전 실패(safety failure)
 * Streeam 명세는 사용되는 함수 객체에 관한 엄중한 규약을 정의
 * 해당 요구사항을 만족하지 않더라도 순차적으로 수행하면 올바른 결과를 얻을 수 있지만
 * 병렬로 수행하면 안전 실패 발생
 *
 *  소스 스트림, 종단 연산, 객체 규약 등등 앞의 조건을 모두 만족하더라도
 * 파이프라인이 수행하는 실작업이 병렬화 추가 코스트를 상쇄 못하면
 * 성능 향상은 미미함
 * 원소 수 + 원소당 수행 코드가 수십만은 되야 성능 향상
 * 
 *  스트림 병렬화는 성능 최적화 수단으로,
 * 반드시 성능 테스트해서 병렬화 사용 가치를 판단해야 함
 *  상당수의 스트림은 병렬화해도 효과가 없으므로
 * 극적으로 성능을 향상시킬 수 있는 특정 조건에 맞는 스트림을 잘 파악해서 적용
 * ex) 머신러닝, 데이터처리
 *
 */
public class item48 {
    public static void main(String[] args) {
        primes().map(p -> BigInteger.TWO.pow(p.intValueExact()).subtract(BigInteger.ONE))
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);
    }

    static Stream<BigInteger> primes() {
        return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
    }

    // 48-3 소수 계산 스트림 파이프라인
    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
                .parallel() // 병렬화
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }
}
