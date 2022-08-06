package ch6.item39;

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*
 * item39. 명명 패턴보다 어노테이션을 사용하라
 *
 *  전통적으로 툴이나 프레임워크가 특별히 다뤄야 할 프로그램 요소에는
 * 딱 구분되는 명명 패턴을 적용해왔음
 * 이는 효과적인 방법이지만 단점도 있음
 * 1. 오차가 나면 안됨
 * 테스트 메소드 이름을 실수로 test로 시작하지 않게 지으면
 * JUnit 3은 이 메소드를 무시하고 지나치므로
 * 개발자는 테스트가 통과했다고 오해할 수 있음
 * 2. 올바른 프로그램 요소에서만 사용되리라 보증할 방법이 없음
 * 메소드가 아니라 클래스 이름을 Test로 시작하게 지어 JUnit에게 던져주면
 * 개발자는 클래스에 정의된 메소드들을 수행해주기를 기대하지만
 * JUnit은 경고 메시지조차 출력하지 않고 테스트는 수행되지 않음
 * 3. 프로그램 요소를 매개변수로 전달할 마땅한 방법이 없음
 * 특정 예외를 던져야 성공하는 테스트가 기대하는 예외 타입을
 * 테스트에 매개변수로 전달해야 하는 상황이라면,
 * 예외 이름을 메소드 이름에 덧붙이는 방법도 있지만,
 * 보기도 나쁘고 깨지기도 쉽고 컴파일러는 이름의 의미를 이해하지 못함
 *  어노테이션은 이 문제를 해결해주는 개념
 * 어노테이션 선언에 다는 어노테이션을 메타어노테이션이라 함
 * @Retention(RetentionPolicy.RUNTIME) 메타어노테이션은
 * @Test가 런타임에도 유지되어야 한다는 표시
 * 이 어노테이션을 생략하면 테스트 툴이 @Test 인식 불가
 * @Target(ElementType.METHOD) 메타어노테이션은
 * @Test가 반드시 메소드 선언에서만 사용돼야 한다고 알림
 *  @Test 어노테이션은 클래스의 의미에 직접적인 영향을 주지 안고,
 * 코드의 의미는 그대로 둔 채 이 어노테이션에 관심 있는
 * 도구에서 특별한 처리를 할 기회를 줌
 *  여러 개의 값을 받는 어노테이션을 배열 매개변수를 받아서 처리하면
 * 매개변수 하나로 구현된 기존 코드들도 수용할 수 있어 유연하고 구현이 간단
 *  @Repeatable 메타어노테이션으로 여러 값을 받는 어노테이션을 만들 수 있음
 * 하지만 컨테이너 어노테이션을 하나 더 정의하고
 * @Repeatable에 컨테이너 어노테이션 class 객체를 매개변수로 전달해야 하며,
 * 내부 어노테이션 타입의 배열을 반환하는 value 메소드도 정의해야 함
 * 또한 적절한 보존 정책(@Retention)과 적용 대상(@Target)을 명시해야 함
 * 반복 가능 어노테이션을 사용해서 코드 가독성을 높일 수 있다면 사용
 * 하지만 어노테이션을 선언하고 처리하는 부분에서는 코드 양이 늘어나고
 * 처리 코드가 복잡해져 오류가 날 가능성이 커짐
 *  어노테이션은 명명 패턴보다 나으며,
 * 어노테이션으로 할 수 있는 일을 명명 패턴으로 처리할 이유가 없음
 *  일반 프로그래머가 어노테이션 타입을 정의할 일은 거의 없지만,
 * 자바 프로그래머라면 자바가 제공하는 어노테이션 타입을 사용해야 함
 *
 *
 *
 */
public class item39 {
    public static void main(String[] args) throws Exception {
        test1();
        test2();
    }

    // 39-3
    private static void test1() throws Exception {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName("ch6.item39.Sample");
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(null);
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(m + " 실패: " + exc);
                } catch (Exception exc) {
                    System.out.println("잘못 사용한 @Test: " + m);
                }
            }
        }
        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
    }

    // 39-5 테스트코드
    private static void test2() throws Exception {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName("ch6.item39.Sample2");
        for (Method m : testClass.getDeclaredMethods()) {
            // isAnnotationPresent 메소드는 반복 가능과 컨테이너 어노테이션을 구분
            if (m.isAnnotationPresent(ExceptionTest.class)
                    || m.isAnnotationPresent(ExceptionTestContainer.class)) {
                tests++;
                try {
                    m.invoke(null);
                    System.out.printf("테스트 %s 실패: 예외를 던지지 않음%n", m);
                } catch (Throwable wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    int oldPassed = passed;
                    ExceptionTest[] excTests =
                            m.getAnnotationsByType(ExceptionTest.class);
                    for (ExceptionTest excTest : excTests) {
                        if (excTest.value().isInstance(exc)) {
                            passed++;
                            break;
                        }
                    }
                    if (passed == oldPassed)
                        System.out.println("잘못 사용한 @ExceptionTest: " + m);
                }
            }
        }
        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
    }


}

// 39-1 마커 어노테이션 타입 선언
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test {
}

// 39-2
class Sample {
    @Test // 성공
    public static void m1() {
    }

    public static void m2() {
    }

    @Test // 실패
    public static void m3() {
        throw new RuntimeException("실패");
    }

    public static void m4() {
    }

    @Test // 잘못 사용 : 정적 메소드가 아님
    public void m5() {
    }

    public static void m6() {
    }

    @Test // 실패
    public static void m7() {
        throw new RuntimeException("실패");
    }

    public static void m8() {
    }
}

// 39-8 반복 가능한 어노테이션
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTestContainer.class)
@interface ExceptionTest {
    Class<? extends Throwable> value();
}

// 39-8 반복 가능한 어노테이션
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ExceptionTestContainer {
    ExceptionTest[] value();
}

// 39-5 매개변수 하나짜리 어노테이션
// 39-6 배열 매개변수를 받는 어노테이션
class Sample2 {
    @ExceptionTest(ArithmeticException.class)
    public static void m11() { // 성공
        int i = 0;
        i = i / i;
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m2() { // 실패 (다른 예외 발생)
        int[] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest(ArithmeticException.class)
    public static void m3() { // 실패 (예외 발생 x)
    }

    // 39-7 배열 매개변수를 받는 어노테이션
    // 39-9 반복 가능 어노테이션을 두 번 단 코드
    @ExceptionTest(IndexOutOfBoundsException.class)
    @ExceptionTest(NullPointerException.class)
    public static void doublyBad() {
        List<String> list = new ArrayList<>();
        list.addAll(5, null);
    }
}
