package ch6.item34;

/*
 * item34. int 상수 대신 열거 타입을 사용하라
 *
 *  열거 타입은 일정 개수의 상수 값을 정의한 다음,
 * 그 외의 값은 허용하지 않는 타입
 * 자바에서 열거 타입을 지원하기 전에는 정수 상수를 한 묶음으로
 * 선언해서 사용했음 : 정수 열거 패턴
 * ex) public static final int SEASON_SPRING = 0;
 * ex) public static final int SEASON_SUMMER = 1;
 *  정수 열거 패턴 기법에는 여러 단점이 있음
 * 타입 안전을 보장할 방법이 없고 표현력도 나쁨
 * 자바가 정수 열거 패턴을 위한 별도의 네임스페이스를 지원하지 않아서
 * 어쩔 수 없이 접두어를 사용해서 이름 충돌을 방지함
 *  정수 열거 패턴을 사용한 프로그램은 깨지기 쉬움
 * 평범한 상수를 나열한 것 뿐이라 컴파일하면 그 값이
 * 클라이언트 파일에 그대로 새겨지는데,
 * 상수 값이 바뀌면 클라이언트도 반드시 재컴파일해야 하므로
 * 만약 다시 컴파일하지 않은 클라이언트가 실행되면 잘못 동작할 것
 *  정수 상수는 문자열로 출력하기에 까다로움
 * 그 값을 출력하거나 디버거로 살펴보면
 * 단지 숫자로 보이기에 이해하는데 도움이 되지 않고,
 * 같은 정수 열거 그룹에 속하는 상수를 순회하는 방법도 없음
 * 심지어 그룹에 속하는 상수가 몇 개인지도 알 수 없음
 *  정수 대신 문자열 상수를 사용하는 변형 패턴도 있음
 * 문자열 열거 패턴이라 하는 이 변형은 더 나쁜데,
 * 상수의 의미를 출려할 수 있다는 점은 좋지만
 * 상수 이름 대신 문자열 값을 그대로 하드코딩할 수 있기 때문
 * 이럴 경우, 하드코딩한 문자열에 오타가 있어도
 * 컴파일러는 확인할 수 없으므로 런타임 버그가 생기며
 * 문자열 비교에 따른 성능 저하도 발생함
 *  다행히 위 문제들을 해결해주며 추가 장점도 있는
 * 열거 타입을 지원함
 * ex) public enum Season { SPRING, SUMMER, FALL, WINTER }
 * 겉보기에는 C에서의 열거 타입과 비슷하지만,
 * 자바의 열거 타입은 완전한 형태의 클래스라서 다른 언어보다 강력함
 *  자바 열거 타입 아이디어는 단순한데, 열거 타입 자체는 클래스이며,
 * 상수 하나당 자신의 인스턴스를 하나씩 만들어 public static final 필도로 공개
 * 열거 타입은 밖에서 접근할 수 있는 생성자를 제공하지 않으므로 사실상 final임
 * 따라서 인스턴스들은 하나씩 존재함이 보장됨
 * item3에서 배운 싱글턴은 원소가 하나 뿐인 열거 타입이라 할 수 있고
 * 반대로 열거 타입은 싱글턴의 일반화 형태라 할 수 있음
 *  열거 타입은 컴파일타임 타입 안전성을 제공
 * 열거 타입을 매개변수로 받는 메소드를 선언했다면,
 * 건네받은 참조는 열거 타입에 소속된 값 중 하나임이 확실함
 * 다른 타입 값을 넘기려 하면 컴파일 오류가 발생함
 *  열거 타입에는 각자의 네임스페이스가 있어서 이름이 같은 상수도 공존함
 * 열거 타입에 새로운 상수를 추가하거나 순서를 바꿔도 재컴파일할 필요가 없음
 * 열거 타입의 toString 메소드는 출력하기에 적합한 문자열을 내어줌
 *  이처럼 열거 타입은 정수 열거 패턴의 단점을 해소해주면서,
 * 임의의 메소드나 필드를 추가할 수도 있고,
 * 임의의 인터페이스를 구현하게 할 수도 있음
 *  열거 타입은 가장 단순하게는 그저 상수 모음일 뿐이지만,
 * 실제로는 클래스이므로 고차원의 추상 개념 하나를 표현해낼 수 있음
 *  행성예제 +
 *  열거 타입 상수 오른쪽에 괄호를 통해서 생성자 매개변수를 지정할 수 있고
 * 생성자에서 데이터를 받아 인스턴스 필드에 저장함으로써
 * 열거 타입 상수 각각을 데이터와 연결지을 수 있음
 * 열거 타입은 근본적으로 불변이라서 모든 필드는 final이어야 하며
 * public으로 선언해도 되지만 private 선언과 public 접근자 메소드를 두는 게 나음
 *  열거 타입은 자신 안에 정의된 상수들의 값을 배열에 담아 반환하는 정적 메소드인 valuesㄹㄹ 제공
 * 값들은 선언 순서대로 저장됨
 * toString 메소드는 상수 이름을 문자열로 반환
 * 기본 toString이 마음에 안들면 원하는 대로 재정의하면 됨
 *  열거 타입에서 상수를 하나 제거해도 제거한 상수를 참조하지 않는
 * 클라이언트에는 아무런 영향이 없음
 * 제가된 상수를 참조하는 클라이언트는 제거 상수를 참조하는 줄에서 컴파일 오류 발생
 * 다시 컴파일하지 않았다면 런타임에서 예외 발생
 *  열거 타입을 선언한 클래스 혹은 크 패키지에서만 유용한 기능은
 * 일반 클래스처럼 그 기능을 노출할 합당한 이유가 없다면
 * private이나 package-private으로 선언
 *  널리 쓰이는 열거 타입은 톱레벨 클래스로 만들고,
 * 특정 톱레벨 클래스에서만 쓰인다면 멤버 클래스로 만들면 됨
 *  사칙연산 연산 열거 타입처럼 실제 연산까지 열거 타입 상수가 수행한다면
 * switch문을 사용해서 구현 가능하지만
 * 새로운 연산이 추가되면 해당 case문도 추가해야 하므로 코드가 깨지기 쉬움
 *  -> 열거 타입에 추상 메소드를 선언하고
 * 각 상수별 클래스 몸체, 즉 각 상수에서 자신에 맞게 재정의하는 방법
 * : 상수별 메소드 구현
 * 새로운 상수를 추가할 때 메소드 구현을 깜박하기 힘들고
 * 깜박하더라도 컴파일 오류로 알려줌
 *  열거 타입에는 상수 이름을 입력받아 그 이름에 해당하는
 * 상수를 반환하는 valueOf(String) 메소드가 자동 생성됨
 * 열거 타입의 toString 메소드를 재정의하려면,
 * toString이 반환하는 문자열을 해당 열거 타입 상수로 변환해주는
 * fromString 메소드도 함께 제공하는 것을 고려
 * stringToEnum 맵과 fromString 메소드로 구현
 * 열거 상수가 stringToEnum 맵에 추가되는 시점은
 * 열거 타입 상수 생성 후 정적 필드가 초기화될 때
 * 왜냐하면, 생성자에서 자신의 인스턴스를 맵에 추가할 수 없기 때문
 * 만약 이 방식이 허용되었다면 런타임에 NullPointerException이 발생
 * 열거 타입의 정적 필드 중 열거 타입 생성자에서 접근할 수 있는 것은
 * 상수 변수뿐
 * 열거 타입 생성자가 실행되는 시점에서는 정적 필드들이 아직 초기화 전
 * 자기 자신을 추가하지 못하게 하는 제약이 꼭 필요함
 * 이 제약의 특수한 예로, 열거 타입 생성자에서
 * 같은 열거 타입의 다른 상수에도 접근할 수 없음
 *  fromString이 Optional 값을 반환하는 점도 주의해야 함
 * 주어진 문자열이 가리키는 연산이 존재하지 않을 수 있음을
 * 클라이언트에게 알리고 알아서 대처하도록 한 것
 *  한편, 상수별 메소드 구현에는 열거 타입 상수끼리
 * 코드를 공유하기 어렵다는 단점
 * switch 문을 이용하여 간결하게 
 * 값에 따라 분기하여 코드를 공유하도록 구현할 수 있지만,
 * 관리 측면에서는 위험함
 * 새로운 값을 열거 타입에 추가하려면
 * 그 값을 처리하는 case 문을 넣어야 하고,
 * 만약 넣지 않았다면 프로그램이 멀쩡하게 돌아가더라도
 * 기대한 것과 다른 결과를 낼 것임
 *  상수별 메소드 구현으로 정확한 동작을 구현하는 방법은
 * 1. 코드를 모든 상수에 중복해서 넣는 방법
 * 2. 코드를 용도에 맞게 나누어 각각을 도우미 메소드로 작성한 다음
 * 각 상수가 자신에게 필요한 메소드를 적절히 호출하는 방법
 * 두 방식 모두 코드가 장황해져서 가독성이 떨어지고 오류 발생 가능성이 높아짐
 *  특정 상수에서만 메소드를 재정의해서 사용하면 장황한 부분을 줄일 수 있지만
 * switch 문과 같은 단점이 발생
 *  가장 깔끔한 방법은 새로운 상수를 추가할 때 전략을 선택하도록 하는 것
 * 계산 부분을 private 중첩 열거 타입으로 옮기고
 * 열거 타입 생성자에서 이 중에서 적당한 것을 선택
 * 그러면 계산 부분을 전략 열거 타입에 위임해서,
 * switch 문이나 상수별 메소드 구현이 필요 없음
 * + 예제 34-9
 * 하지만 기존 열거 타입에 상수별 동작을 혼합해 넣을 때에는
 * switch 문이 좋은 선택이 될 수 있음
 * 추가 메소드가 의미상 열거 타입에 속하지 않는다면
 * 직접 만든 열거 타입이라도 switch 방식이 좋음
 *  대부분의 경우 열거 타입 성능은 정수 상수와 다르지 않음
 *  필요한 원소를 컴파일타임에 다 알 수 있는 상수 집합이라면
 * 항상 열거 타입을 사용
 * 태양계 행성, 요일, 체스 말처럼 본질적으로 열거 타입이거나
 * 메뉴 아이템, 연산 코드 등과 같이 허용하는 값 모두를 이미 알고 있는 경우
 * 열거 타입에 정의된 상수 개수가 영원히 고정 불변일 필요는 없음
 * 나중에 상수가 추가돼도 호환되도록 설계되어 있음
 *
 * 
 *
 *
 *
 * 
 */
public class item34 {
    // 34-4 값에 따라 분기하는 열거 타입
    /*
    public enum Operation {
        PLUS, MINUS, TIMES, DIVIDE;

        public double apply(double x, double y) {
            switch (this) {
                case PLUS -> {return x+y;}
                case MINUS -> {return x-y;}
                case TIMES -> {return x*y;}
                case DIVIDE -> {return x/y;}
            }
            throw new AssertionError("알 수 없는 연산: " + this);
        }
    }
     */

    // 34-5 상수별 메소드 구현을 활용한 열거 타입
    // 34-6 상수별 클래스 몸체와 데이터를 사용한 열거 타입
    public enum Operation {
        PLUS("+") {
            public double apply(double x, double y) {
                return x+y;
            }
        },
        MINUS("-") {
            public double apply(double x, double y) {
                return x-y;
            }
        },
        TIMES("*") {
            public double apply(double x, double y) {
                return x*y;
            }
        },
        DIVIDE("/") {
            public double apply(double x, double y) {
                return x/y;
            }
        };

        private final String symbol;

        Operation(String symbol) {this.symbol = symbol;}

        @Override
        public String toString() {
            return symbol;
        }

        public abstract double apply(double x, double y);
    }

    // 34-9 전략 열거 타입
    enum PayrollDay {
        MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY), WENDSDAY(PayType.WEEKDAY),
        THURSDAY(PayType.WEEKDAY), FRIDAY(PayType.WEEKDAY),
        SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);
        
        private final PayType payType;
        
        PayrollDay(PayType payType) {this.payType=payType;}
        
        int pay(int minutesWorked, int payRate) {
            return payType.pay(minutesWorked, payRate);
        }
        
        // 전략 열거 타입
        enum PayType {
            WEEKDAY {
                int overtimePay(int minsWorked, int payRate) {
                    return minsWorked <= MINS_PER_SHIFT ? 0 :
                            (minsWorked - MINS_PER_SHIFT) * payRate / 2;
                }
            },
            WEEKEND {
                int overtimePay(int minsWorked, int payRate) {
                    return minsWorked * payRate / 2;
                }
            };

            abstract int overtimePay(int mins, int payRate);
            private static final int MINS_PER_SHIFT = 8 * 60;

            int pay(int minsWorked, int payRate) {
                int basePay = minsWorked * payRate;
                return basePay + overtimePay(minsWorked, payRate);
            }
        }
    }
}
