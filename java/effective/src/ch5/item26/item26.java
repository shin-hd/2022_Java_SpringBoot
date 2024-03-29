package ch5.item26;

import java.util.Set;

/*
 * item26. 로 타입은 사용하지 말라
 *
 *  타입 매개변수가 쓰이면 제네릭 클래스, 제네릭 인터페이스
 * 둘을 통틀어 제네릭 타입
 *  제네릭 타입을 하나 정의하면 그에 딸린 로(raw) 타입도 함께 정의됨
 * 로 타입이란? 제네릭 타입에서 타입 매개변수를 전혀 사용하지 않을 때
 * 로 타입은 타입 선언에서 제네릭 타입 정보가 전부 지워진 것처럼 동작하는데,
 * 제네릭 도래 전 코드와 호환되도록 하기 위한 궁여지책
 *  1. 로 타입을 사용하면 의도하지 않은 인스턴스를 넣어도
 * 오류 없이 컴파일되고 실행되며, 오류를 알기 힘듦
 * 오류는 가능한 한 발생 즉시, 이상적으로는 컴파일할 때 발견하는 것이 좋음
 *  제네릭을 활용하면 타입 정보가 주석이 아닌 타입 선언 자체에 적용
 * 경고 없이 컴파일되면 의도대로 동작함을 보장
 *  2. 로 타입을 쓰면 제네릭이 안겨주는 안전성과 표현력을 모두 잃음
 * 로 타입을 만든 이유는? 호환성 때문
 * 자바가 제네릭을 받아들이기까지 10년이 걸려서
 * 그동안 제네릭 없는 코드가 많이 생김
 * 기존의 코드를 모두 수용하면서 제네릭을 사용하는 코드도 돌아가게 하기 위해
 * 로 타입을 지원하고 제네릭 구현에는 소거 방식을 사용
 *  3. 임의 객체를 허용하는 매개변수화 타입은 괜찮음
 * 로 타입인 List와 매개변수화 타입인 List<Object>의 차이는
 * 모든 타입을 혀용한다는 의사를 컴파일러에 전달했다는 것
 *  4. 로 타입 대신 비한정적 와일드카드 타입을 사용하는 게 좋음
 * 제네릭 타입을 쓰고 싶지만 실제 타입 매개변수가 무엇인지 신경 쓰고 싶지 않다면 ?를 사용
 * ex) 제네릭 타입 Set<E>의 비한정적 와일드카드 타입은 Set<?>
 * 비한정적 와일드카드 타입은 안전하고 로 타입은 안전하지 않음
 * 로 타입 컬렉션은 아무 원소나 넣을 수 있어 타입 불변식을 훼손하기 쉽지만
 * 비한정적 와일드카드 타입 컬렉션은 어떤 원소도 넣을 수 없음
 * 비한정적 와일드카드 타입의 제약이 싫다면
 * 제네릭 메소드나 한정적 와일드카드 타입을 사용하면 됨
 *  5. 로 타입 예외 몇 가지
 *  class 리터럴에는 로 타입을 써야 함
 * 자바 명세는 class 리터럴에 매개변수화 타입을 사용하지 못하게 했음
 * (List.class, int.class는 허용)
 * (List<String>.class, List<?>.class는 허용 x)
 *  런타임에는 제네릭 타입 정보가 지워지므로
 * instanceof 연산자는 비한정적 와일드카드 타입 이외의
 * 매개변수화 타입에 적용할 수 없고
 * 로 타입이든 비한정적 외일드카드 타입이든 instanceof는 완전히 똑같이 동작
 * 비한정적 와일드카드 타입의 꺽쇠괄호와 물음표는 의미없이 코드만 지저분해지므로 로 타입 사용
 *
 */
public class item26 {

    static boolean isInstanceOfSet(Object o) {
        if(o instanceof Set) {
            Set<?> s = (Set<?>) o;
            return true;
        }
        return false;
    }

}
