package ch4.item23;

/*
 * 태그 달린 클래스보다는 클래스 계층구조를 활용하라
 *
 * 두 가지 이상의 의미를 표현할 수 있으며,
 * 현재 표현하는 의미를 태그 값으로 알려주는 클래스는
 * 단점이 한가득임 + 예제
 *
 * 1. 태그 달린 클래스는 장황하고, 오류를 내기 쉽고, 비효율적
 * 열거 타입 선언, 태그 필드, 스위치문 등 쓸데없는 코드가 많음
 * 여러 구현이 한 클래스에 혼합되어 있어 가독성이 나쁨
 * 의미없는 코드가 공존해서 불필요하게 메모리를 사용
 * 필드를 final로 선언하면 쓰이지 않는 필드까지 초기화해야 함
 * 의미를 추가하려면 코드를 수정해야 함
 * 인스턴스 타입만으로 의미를 알 수 없음
 *
 * 2. 태그 달린 클래스는 클래스 계층구조를 흉내낸 아류임
 * 클래스 계층구조를 활용하는 서브타이핑이 존재함
 *  태그 달린 클래스를 클래스 계층구조로 바꾸려면
 * 먼저 root가 될 추상 클래스를 정의하고, 동작이 달라지는 메소드를 추상 메소드로 선언
 * 동작이 일정한 메소드를 루트 클래스의 일반 메소드로 추가
 * 모든 하위 클래스에서 공통으로 사용하는 데이터 필드도 루트 클래스로 올림
 * 다음으로, 루트 클래스를 확장한 구체 클래스를 의미별로 하나씩 정의
 * 예) Figure -> Circle, Rectangle, Triangle 등
 *
 *
 */
public class item23 {
    class Figure {
        enum Shape { RECTANGLE, CIRCLE };

        final Shape shape;

        double length;
        double width;

        double radius;

        Figure(double radius) {
            shape = Shape.CIRCLE;
            this.radius = radius;
        }

        Figure(double length, double width) {
            shape = Shape.RECTANGLE;
            this.length = length;
            this.width = width;
        }

        double area() {
            switch (shape) {
                case RECTANGLE:
                    return length * width;
                case CIRCLE:
                    return Math.PI * (radius * radius);
                default:
                    throw new AssertionError(shape);
            }
        }
    }
}
