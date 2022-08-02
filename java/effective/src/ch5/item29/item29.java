package ch5.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

/*
 * item29. 이왕이면 제네릭 타입으로 만들어라
 *
 *  JDK가 제공하는 제네릭 타입과 메소드를 사용하는건 쉽지만
 * 제네릭 타입을 새로 만드는 일은 좀 더 어려움
 * 
 *  1. 일반 클래스를 제네릭 클래스로 만드는 첫 단계는
 * 클래스 선언에 타입 매개변수를 추가하는 일
 * 이 때 타입 이름으로 보통 E를 사용
 *  2. 그 다음 코드에 쓰인 Object를 적절한 타입 매개변수로 바꿈
 * E와 같은 실체화 불가 타입으로는 배열을 만들 수 없으므로
 * 배열을 사용하는 코드를 제네릭으로 만드는 데 문제가 발생
 * 해결 방법 두가지
 * 1) 제네릭 배열 생성을 금지하는 방법을 대놓고 우회하는 방법
 * Object 배열을 생성한 다음 제네릭 배열로 형변환
 * 하지만 일반적으로 타입 안전하지 않음
 * 컴파일러가 타입 안전을 증명할 수 없으므로
 * 프로그래머가 직접 타입 안전을 확인해야 함
 * 2) elements 필드의 타입을 E[]에서 Object[]로 바꾸는 것
 * 배열이 반환한 원소를 E로 형변환하면 경고 발생
 * E로 형변환하면 컴파일러가 안전성 증명 불가
 *  첫 번째 방법이 가독성이 좋고 코드도 더 짧으며
 * 배열 생성 시에만 형변환이 발생하므로 협업에서는 첫 번째 방식 선호
 * 하지만 배열 런타임 타입이 컴파일 타입과 달라 힙 오염 발생
 *  지금까지 설명은 item28의 "배열과 리스트를 우선하라"와 모순되 보이며,
 * 사실 제네릭 타입 안에서 리스트를 사용하는게 항상 가능하지도, 꼭 더 좋은 것도 아님
 * 자바가 List를 기본 타입으로 제공하지 않으므로
 * 제네릭 타입도 결국 기본 배열을 사용해 구현해야 함
 * 또한 어떤 타입은 성능을 목적으로 배열을 사용하기도 함
 *  대다수의 제네릭 타입은 타입 매개변수에 아무런 제약을 두지 않음
 * 단, 기본 타입은 사용할 수 없음
 * 이는 박싱 타입을 사용해 우회할 수 있음
 *  타입 메개변수에 제약을 두는 제네릭 타입도 있음
 * ex) DelayQueue<E extends Delayed>는 Delayed의 하위 타입만 받는다는 뜻
 * 이렇게 하면 형변환 걱정을 덜 수 있음
 * 이런 타입 매개변수 E를 한정적 타입 매개변수라 함
 * 모든 타입은 자기 자신의 하위 타입이므로 <Delayed>로도 사용가능
 *
 *
 */
public class item29 {
    class Stack<E> {
        // 두 번째 방법
        private Object[] elements;
        private int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;

        // 첫 번째 방법
        // @SuppressWarnings("unchecked")
        public Stack() {
            // 실체화 불가 타입으로는 배열 생성 불가
            elements = new Object[DEFAULT_INITIAL_CAPACITY];

            // 첫번째 방법 // 타입 안전하지 않음
            //elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
        }

        public void push(E e) {
            ensureCapacity();
            elements[size++] = e;
        }

        public E pop() {
            if(size == 0)
                throw new EmptyStackException();
            // 두 번째 방법 // E로 형변환
            @SuppressWarnings("unchecked")
            E result = (E)elements[--size];
            elements[size] = null;
            return result;
        }

        private void ensureCapacity() {
            if(elements.length == size)
                elements = Arrays.copyOf(elements, 2*size+1);
        }
    }
}
