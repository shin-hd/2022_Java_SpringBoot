package ch2.Item7;

import java.util.Arrays;
import java.util.EmptyStackException;

/*
 * (가비지 컬렉터가 회수할 수 있도록) 다 쓴 객체 참조를 해제하라
 * - 가비지 컬렉터를 가진 언어에서도 회수를 못해 메모리 누수가 발생한다
 *
 * 다 쓴 참조를 해제하는 가장 좋은 방법은
 * 참조 변수를 스코프 밖으로 밀어내는 것
 * 객체 참조를 null 처리하는 일은 예외적인 경우!
 *
 * 메모리 누수의 주범
 * 1. (배열처럼) 메모리를 직접 관리하는 클래스
 *    -> 원소를 다 사용한 즉시 null 처리
 * 2. 캐시
 *    -> WeakHashMap 사용
 *       or 시간이 지날수록 엔트리 가치 떨어뜨리는 방식 사용
 * 3. 리스너, 콜백
 *    -> 약한 참조(week reference)로 저장 시 수거됨
 */
public class Item7 {

    public class Stack {
        
        // 배열의 활성 영역/비활성 영역을 가비지 컬렉터가 알 수 없어서
        // 메모리 누수 발생
        // -> null 처리로 객체 사용하지 않음을 알려야
        private Object[] elements;
        private int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;

        public Stack() {
            elements = new Object[DEFAULT_INITIAL_CAPACITY];
        }

        public void push(Object e) {
            ensureCapacity();
            elements[size++] = e;
        }

        public Object pop() {
            if(size == 0)
                throw new EmptyStackException();

            // return elements[--size]; // 메모리 누수 발생
            Object result = elements[--size];
            elements[size] = null; // 참조 해제
            return result;
        }

        private void ensureCapacity() {
            if(elements.length == size)
                elements = Arrays.copyOf(elements, 2*size+1);
        }
    }


}
