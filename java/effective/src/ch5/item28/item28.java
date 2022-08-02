package ch5.item28;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/*
 * item28. 배열보다는 리스트를 사용하라
 *
 *  배열과 제네릭 타입에는 중요한 차이가 두 가지 있음
 * 1. 베얄은 공변(covariant)
 * Sub가 Super의 하위 타입이라면 Sub[]는 Super[]의 하위 타입
 * (공변. 즉 같이 변함)
 * 반면, 제네릭은 불공변(invariant)
 * 서로 다른 타입 T1, T2가 있을 때,
 * List<T1>은 List<T2>와 상위 타입, 하위 타입이 아님
 * 배열과 리스트 각각에 호환되지 않는 타입을 넣으면
 * 배열에서는 실수를 런타임에서 알게 되지만
 * 리스트를 사용하면 컴파일할 때 바로 알 수 있음
 * 2. 배열은 실체화됨
 * 배열은 런타임에도 자신이 담기로 한 원소 타입을 인지하고 확인함
 * 호환되지 않는 타입을 넣으려 하면 예외 발생
 * 반면, 제네릭은 타입 정보가 런타임에서는 소거(erasure)
 * 원소 타입을 컴파일타임에만 검사하고 런타임에는 알 수 없음
 *  이 주요 차이때문에 배열과 제네릭은 잘 어울리지 못함
 * 배열은 제네릭 타입, 매개변수화 타입, 타입 매개변수로 사용할 수 없음
 *  제네릭 배열을 만들지 못하게 막은 이유는 타입 안전하지 않기 때문
 * 이를 허용하면 컴파일러가 자동 생성한 형변환 코드에서
 * 런타입에 ClassCastException이 발생할 수 있는데,
 * 이러면 제네릭 타입 시스템 취지에서 벗어남
 * E, List<E>, List<String> 같은 타입을 실체화 불가 타입이라 함
 * 실체화되지 않아서 런타임에는 컴파일타임보다 타입 정보를 적게 가지는 타입
 * 소거 메커니즘때문에 매개변수화 타입 가운데 실체화 가능한 타입은
 * 비한정적 와일드카드 타입 뿐
 *  배열로 형변환할 때 제네릭 배열 생성 오류나 비검사 형변환 경고가 뜨는 경우
 * 대부분은 배열 대신 컬렉션을 사용하면 해결됨
 * 코드가 조금 복잡해지고 성능이 약간 저하되지만, 타입 안전성과 상호운영성이 좋아짐
 *
 */
public class item28 {

    /*
     * choose 메소드를 호출할 때마다
     * 반환된 Object를 원하는 형태로 형변환해야 함
     * 타입이 다른 원소가 들어있었다면 런타임에 형변환 오류 발생
     */
    class Chooser {
        private final Object[] choiceArray;

        public Chooser(Collection choices) {
            choiceArray = choices.toArray();
        }
        public Object choose() {
            Random rnd = ThreadLocalRandom.current();
            return choiceArray[rnd.nextInt(choiceArray.length)];
        }
    }

    /* 제네릭 적용
    class Chooser<T> {
        private final List<T> choiceList;
        public Chooser(Collection<T> choices) {
            choiceArray = new ArrayList<>(choices);
        }
        public T choose() {
            Random rnd = ThreadLocalRandom.current();
            return choiceList.get(rnd.nextInt(choiceList.size()));
        }
    }
     */
}
