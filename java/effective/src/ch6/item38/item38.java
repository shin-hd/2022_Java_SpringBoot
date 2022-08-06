package ch6.item38;

/*
 * item38. 확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라
 *
 *  열거 타입은 거의 모든 상황에서 타입 안전 열거 패턴보다 우수함
 * 예외가 하나 있는데, 타입 안전 열거 패턴은 확장할 수 있지만
 * 열거 타입은 그럴 수 없음
 * 열거 타입을 확장하지 못하게 한 이유는
 * 대부분의 상황에서 열거 타입을 확장하는 것은 좋은 방법이 아니기 때문
 * 확장한 타입의 원소는 기반 타입의 원소로 취급되지만,
 * 그 반대는 성립하지 않는다면 이상함
 * 기반 타입과 확장된 타입들의 원소 모두를 순회하는 방법도 없음
 * 확장성을 높이려면 고려할 요소가 늘어나 설계와 구현이 더 복잡해짐
 *  그런데 확장할 수 있는 열거 타입이 어울리는 쓰임이 최소 하나는 존재
 * 연산 코드(operation code, opcode)
 * 연산 코드의 각 원소는 특정 기계가 수행하는 연산을 뜻함
 * 이따금 API가 제공하는 기본 연산 외에 사용자 확장 연산을
 * 추가할 수 있도록 열어주어야 할 때가 있음
 *  열거 타입으로도 이 효과를 내는 방법이 있는데,
 * 기본 아이디어는 열거 타입이
 * 임의의 인터페이스를 구현할 수 있다는 사실을 이용하는 것
 * 연산 코드용 인터페이스를 정의하고 열거 타입이 이 인터페이스를 구현하게 하면 됨
 * 이 때 열거 타입이 그 인터페이스의 표준 구현체 역할을 함
 *  개별 인스턴스 수준에서뿐 아니라 타입 수준에서도, 기본 열거 타입 대신
 * 확장된 열거 타입을 넘겨 확장된 열거 타입의 원소 모두를 사용하게
 * 할 수도 있음
 * 인터페이스를 이용해 확장 가능한 열거 타입을 흉내내는 방식에도 사소한 문제가 있는데
 * 열거 타입끼리 구현을 상속할 수 없다는 점
 * 아무 상태에도 의존하지 않는 경우 디폴트 구현을 이용해
 * 인터페이스에 추가하는 방법이 있음
 * 중복량이 적으면 문제가 되지는 않지만, 공유하는 기능이 많다면 그 부분을
 * 별도의 도우미 클래스나 정적 도우미 메소드로 분리해서 코드 중복을 없앨 수 있음 
 *
 * 
 *
 */
public class item38 {
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        test(ExtendedOperation.class, x, y);
        // test(Arrays.asList(ExtendedOperation.values()), x, y);
    }

    public static <T extends Enum<T> & Operation> void test(
            Class<T> opEnumType, double x, double y) {
        for(Operation op : opEnumType.getEnumConstants())
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
    }
    // public static void test(Collection<? extends Operation> opSet, double x, double y) {
    // for (Operation op : opSet)
    //      System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y)); }

}

// 38-1 인터페이스를 이용한 확장 가능 열거 타입
interface Operation {
    double apply(double x, double y);
}

enum BasicOperation implements Operation {
    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}

enum ExtendedOperation implements Operation {
    EXP("^") {
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}