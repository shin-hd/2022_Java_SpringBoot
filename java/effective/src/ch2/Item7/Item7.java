package ch2.Item7;

import java.util.Arrays;
import java.util.EmptyStackException;

/*
 * 다 쓴 객체 참조를 해제하라
 *
 * 메모리 누수의 주범
 * 1. 메모리를 직접 관리하는 클래스
 * 2. 캐시
 * 3. 리스너, 콜백
 *    -> 약한참조로 지정 시 수거됨
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
