package ch5.item30;

import java.util.*;
import java.util.function.UnaryOperator;

/*
 * item30. 이왕이면 제네릭 메소드로 만들어라
 *
 *  메소드도 제네릭으로 만들 수 있음
 *  제네릭 메소드 작석법은 제네릭 타입 작성법과 비슷함
 * 메소드 선언에서의 집합의 원소 타입을 타입 매개변수로 명시하고,
 * 메소드 안에서도 이 타입 매개변수만 사용하게 수정
 * 타입 매개변수 목록은 매소드 제한자와 반환 타입 사이에 옴
 *  때때로 불변 객체를 여러 타입으로 활용할 수 있게 해야 할 때가 있는데
 * 제네릭은 런타임에 타입 정보가 소거되므로 하나의 객체를 어떤 타입으로든 매개변수화 가능
 * 하지만 이렇게 하려면 요청 타입 매개변수에 맞게 매번
 * 객체 타입을 바꿔주는 정적 팩토리를 만들어야 함
 * : 제네릭 싱글턴 팩터리
 *  30-4의 항등함수 예제를 보면,
 * 항등함수 객체는 상태가 없으니 요청할 때마다 새로 생성하는 것은 낭비
 * 제네릭이 실체화된다면 항등함수를 타입별로 하나씩 만들어야 하지만,
 * 소거 방식을 사용해서 제네릭 싱글턴 하나면 충분
 * IDENTITY_FN을 UnaryOperator<T>로 형변환하면 경고가 발생하는데,
 * T가 어떤 타입이든 UnaryOperator<Object>는 UnaryOperator<T>가 아니기 때문
 * 하지만 T가 어떤 타입이든 UnaryOperator<T>를 사용해도 타입 안전
 * 이 사실을 알기에 경고 감춤
 *  드물지만, 자기 자신이 들어간 표현식을 사용해서 타입 매개변수의 허용 법위를 한정할 수 있음
 * 재귀적 타입 한정이라는 개념으로
 * 주로 타입의 자연적 순서를 정하는 Comparable 인터페이스와 함께 쓰임
 *  Comparable을 구현한 원소의 컬렉션을 입력받는 메소드들은 주로
 * 그 원소를 정렬 혹은 검색하거나, 최대-최소를 구하는 식으로 사용됨
 * 이 기능을 수행하려면 컬렉션에 담긴 모든 원소가 상호비교 가능해야 함
 * 타입 한정인 <E extends Comparable<E>>는
 * 모든 타입 E는 자신과 비교할 수 있다는 뜻으로 상호 비교 가능하다는 의미를 정확히 표현
 *
 */
public class item30 {
    // 30-2
    public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }

    // 30-4 제네릭 싱글턴 팩토리 패턴
    private static UnaryOperator<Object> IDENTITY_FN = t -> t;
    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    // 30-7 재귀적 타입 한정
    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if(c.isEmpty())
            throw new IllegalArgumentException("컬렉션이 비어 있음");

        E result = null;
        for (E e: c)
            if(result == null || e.compareTo(result) > 0)
                result = Objects.requireNonNull(e);

        return result;
    }
}
