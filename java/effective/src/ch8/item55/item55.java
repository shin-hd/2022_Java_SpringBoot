package ch8.item55;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/*
 * item55. 옵셔널 반환은 신중히 하라
 *
 *  메소드가 특정 조건에서 값을 반환할 수 없을 때 취할 수 있는 선택지
 * 자바 8 이전
 * 1. 예외 던지기
 *    예외는 진짜 예외적인 상황에서만 사용해야 하며,
 *    예외를 생성할 때 스택 전체를 캡처해서 비용이 큼
 * 2. null 반환
 *    별도 null 처리 코드를 추가해야 하고,
 *    null 처리가 제대로 안되면 NullPointerException 발생 가능
 * 자바 8 이후
 * 3. 옵셔널 반환
 *    Optional<T>는 null이 아닌T 참조를 하나 담거나 아무것도 담지 않음
 *    옵셔널은 원소를 최대 1개 가질 수 있는 불변 컬렉션
 *
 *  T를 반환해야 하지만 특정 조건에서는 아무것도 반환하지 않아야 할 때
 * Optional<T>를 반환하도록 선언
 * 예외를 던지는 메소드보다 유연하고 사용이 쉬움
 * null을 반환하는 메소드보다 오류 가능성이 작음
 *
 *  옵셔널 반환을 선택하는 기준
 * 옵셔널은 반환 값이 없을 수도 있음을 명확히 알려줌
 * 메소드가 옵셔널을 반환한다면 클라이언트는 값을 받지 못했을 때
 * 취할 행동을 선택해야 함
 * 1. 기본값 설정
 *    ex) max(words).orElse("단어 없음...");
 * 2. 상황에 맞는 예외 던지기
 *    ex) max(toys).orElseThrow(TemperTantrumException:new);
 * 3. 옵셔널에 항상 값이 채워져있다고 확신한다면 그냥 바로 사용
 *    ex) max(Elements.NOBLE_GASES).get();
 *
 *  기본값 설정 비용이 커서 부담될 수 있는데,
 * Supplier<T>를 인수로 받는 orElseGet을 사용하면
 * 값이 처음 필요할 때 초기 설정 비용을 낮출 수 있음
 *
 *  isPresent 메소드는 안전 벨브 역할의 메소드로,
 * 옵셔널이 채워져 있으면 true, 비었으면 false를 반환
 * 하지만 다른 기본 메소드로 더 짧고 명확하게 대체 가능
 *
 *  반환값으로 옵셔널을 사용하는 게 무조건 좋은건 아님
 * 컬렉션, 스트림, 배열, 옵셔널같은 컨테이너 타입은 옵셔널로 감싸면 안 됨
 * Optional<List<T>>보다는 빈 List<T>를 반환
 *
 *  반환 타입을 T 대신 Optional<T>로 선언해야 하는 경우
 * : 결과가 없을 수 있으면서 클라이언트가 이 상황을 특별하게 처리해야 하는 경우
 * Optional도 엄연히 할당하고 호출 해야하는 객체라서
 * 성능이 중요한 상황에서는 옵셔널이 안맞을 수 있음
 *
 *  박싱된 기본 타입을 담는 옵셔널은 두 겹이나 감싼게 되어
 * 불필요하게 무거워짐
 * => int, long, double 전용 옵셔널 클래스
 * OptionalInt, OptionalLong, OptionalDouble
 * 따라서 방싱된 기본 타입을 담은 옵셔널을 반환하지 말아야 함
 *
 *  옵셔널을 컬렉션 키, 값, 원소나 배열 원소로 사용하면
 * 복잡성을 높여서 혼란과 오류 가능성이 증가하므로 사용 x
 *
 *  옵셔널 필드는 대부분 상황에서 별로
 * 필드가 기본 타입이라 값이 없음을 나타낼 방법이 없고
 * 필드 상당수가 필수가 아니라면 옵셔널이 나을 수도
 *
 */
public class item55 {
    // 55-2
    public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
        if(c.isEmpty())
            return Optional.empty();

        E result = null;
        for (E e : c)
            if (result == null || e.compareTo(result) > 0)
                result = Objects.requireNonNull(e);

        // result가 null이면 NullPointerException
        return Optional.of(result);
    }
}
