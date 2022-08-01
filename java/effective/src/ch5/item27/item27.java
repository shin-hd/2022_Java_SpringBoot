package ch5.item27;

import java.util.Arrays;

/*
 * item27. 비검사 경고를 제거하라
 *
 *  제네릭을 사용하면 많은 컴파일러 경고를 보게 되는데
 * 대부분의 비검사 경고는 쉽게 제거할 수 있음
 * 타입 매개변수를 명시하지 않고 다이아몬드 연산자를 사용하면
 * 컴파일러가 타입 매개변수를 추론해줌
 *  제거하기 어려운 경고도 있는데, 할 수 있는 한 모든 비검사 경고를 제거해야
 * 타입 안전성이 보장됨
 *
 *  경고를 제거할 수는 없지만 타입 안전하다고 확신할 수 있다면
 * @SuppressWarnings("unchecked") 어노테이션을 달아 경고를 숨기자
 * 타입 안전함을 제대로 검증하지 않고 경고를 숨기면
 * 런타임에서 여전히 예외를 던질 수 있음
 * 반면에, 타입 안전하다고 검증된 비검사 경고를 그대로 두면
 * 진짜 문제를 알리는 경고가 나와도 파묻혀 인지하지 못할 수 있음
 *
 *  @SuppressWarnings 어노테이션은 어떤 선언에도 달 수 있지만
 * 항상 가능한 한 좁은 범위에 적용해야 함
 * 심각한 경고를 놓칠 수 있으니 절대 클래스 전체에 적용해선 안됨
 *
 *  한 줄이 넘는 메소드나 생성자에 달린 @SuppressWarnings 어노테이션은
 * 지역변수 선언 쪽으로 옮겨야 함
 * 어노테이션은 선언에만 달 수 있으므로 return문에는 @SuppressWarning을 다는 게 불가능
 * 메소드 전체에 다는 것은 범위가 필요 이상으로 넓어지므로
 * 반환값을 담은 지역변수를 선언하고, 그 변수에 어노테이션을 달아주면 됨
 *
 *  @SuppressWarnings("unchecked") 어노테이션을 사용할 때면
 * 경고를 무시한다고 해도 안전한 이유를 주석으로 넘겨야 함
 * 다른 사람이 코드를 이해하는 데 도움이 되며,
 * 코드를 잘못 수정해서 타입 안전성을 잃는 상황을 줄여줌
 *
 */
public class item27 {
    private int size = 0;
    private Object[] elements;

    public <T> T[] toArray(T[] a) {
        if(a.length < size) {
            @SuppressWarnings("unchecked") T[] result =
                    (T[]) Arrays.copyOf(elements, size, a.getClass());
            return result;
        }
        System.arraycopy(elements, 0, a, 0, size);
        if(a.length > size)
            a[size] = null;
        return a;
    }
}
