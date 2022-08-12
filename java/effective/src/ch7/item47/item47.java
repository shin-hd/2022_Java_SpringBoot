package ch7.item47;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/*
 * item47. 반환 타입으로는 스트림보다 컬렉션이 낫다
 *
 *  스트림은 반복(iteration)을 지원하지 않음
 * 따라서 스트림과 반복을 알맞게 조합해야 좋은 코드가 나옴
 * 사실 Stream 인터페이스는 Iterable 인터페이스가 정의한 추상 메소드를
 * 전부 포함할뿐만 아니라, Iterable 인터페이스가 정의한 방식대로 동작
 * 그럼에도 forEach로 스트림을 반복할 수 없는 이유는
 * Stream이 Iterable을 extends하지 않았기 때문
 *  해결책
 * 1. Stream의 iterator 메소드에 메소드 참조를 건네기
 *    => 작동은 하지만 난잡하고 직관성이 떨어짐
 * 2. Stream<E>을 Iterable<E>로 중개해주는 어댑터 메소드 사용(47-3)
 *    => 자바 타입 추론이 문맥을 파악해서 별도 형변환이 필요 없음
 *  API가 Iterable만 반환해도 스트림 파이프라인에서 처리하려면 불편할 수 있음
 * Iterable을 Stream으로 중개하는 어댑터도 쉽게 구현 가능
 *
 * 반환 시퀀스가 스트림 파이프라인에서만 쓰임 => 스트림 반환
 *             반복문에서만 쓰임         => Iterable 반환
 *
 * 공개 API는 양 사용자 모두를 배려해야 함
 *  Collection은 반복과 스트림을 동시에 지원하기 때문에,
 * 원소 시퀀스를 반환하는 공개 API의 반환 타입은
 * Collection이나 그 하위 타입을 쓰는 게 최선
 *
 *  반환하는 시퀀스 크기가 충분히 작다면 표준 컬렉션 구현체를 반환해도 되지만,
 * 컬렉션을 반환한다고 큰 시퀀스를 메모리에 올리면 안됨
 * => 반환 시퀀스카 크지만 표현을 간결히 할 수 있다면 전용 컬렉션 구현
 *
 *  AbstractCollection을 활용해서 Collection 구현체를 작성할 때는
 * Iterable용 메소드 외에 contains와 size만 구현하면 됨
 * 만약 contains와 size의 구현이 불가능하다면
 * 컬렉션보다는 스트림이나 Iterable을 반환하도록
 *
 *  정리하자면, 컬렉션을 반환할 수 있다면 컬렉션을 반환하고,
 * 불가능하면 스트림과 Iterable 중 더 자연스러운 것을 반환
 */
public class item47 {
    public static void main(String[] args) {
        // 47-2
        for (ProcessHandle ph : (Iterable<? extends ProcessHandle>) ProcessHandle.allProcesses()::iterator) {
        }

        // 47-3
        for (ProcessHandle p : iterableOf(ProcessHandle.allProcesses())) {
        }
    }

    // 47-3 Stream<E>을 Iterable<E>로 중개해주는 어댑터
    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }

    // 47-4 Iterable<E>을 Stream<E>로 중개해주는 어댑터
    public static <E> Stream<E> streamOf(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
