package ch4.item17;

/*
 * 변경 가능성을 최소화하라
 *
 * 불변 클래스란 인스턴스 내부 값을 수정할 수 없는 클래스
 * String, 기본 타입 박싱 클래스, BigInteger, BigDecimal 등이 불변 클래스
 *
 * 클래스를 불변으로 만드는 규칙 5가지
 * 1. 객체 상태를 변경하는 메소드를 제공하지 않음
 * 2. 클래스 확장할 수 없도록 함
 *    하위 클래스에서 객체 상태 변경하는 것을 막음
 * 3. 모든 필드를 final로 선언
 *    설계자의 의도를 명확히 드러내는 방법
 * 4. 모든 필드를 private으로 선언
 *    필드가 참조하는 가변 객체를 직접 접근해 수정하는 일을 막아줌
 * 5. 자신 외에는 내부 가변 컴포넌트에 접근할 수 없도록 함
 *    클래스에 가변 객체를 참조하는 필드가 있다면
 *    클라이언트에서 그 객체의 참조를 얻을 수 없도록 해야 함
 *    -> 방어적 복사를 수행
 *
 *
 * =========== 불변 클래스의 장점 ===========
 * ---------------------------------------
 * + 예제
 * 
 * 함수형 프로그래밍
 * 피연산자에 함수를 적용해 결과를 반환하지만,
 * 피연산자 자체는 그대로인 프로그래밍
 * 메소드 이름이 전치사
 * 
 * 절차적, 명령형 프로그래밍
 * 메소드에 피연산자인 자신을 수정해 자신의 상태가 변하게 됨
 * 메소드 이름이 동사
 * ----------------------------------------
 *
 * 불변 객체는 근본적으로 스레드 안전해서 동기화할 필요가 없음
 * 클래스를 스레드 안전하게 만드는 가장 쉬운 방법
 * 안심하고 공유 가능
 * 따라서 불변 클래스라면 최대한 재활용하기를 권함
 *
 * 재활용 방법
 * - 자주 쓰이는 값을 상수(public static final)로 제공
 * - 자주 사용되는 인스턴스를 캐싱하는 정적 팩토리 제공
 *
 * 불변 객체는 복사해도 원본과 같아서 복사의 의미가 없음
 * 따라서 clone 메소드나 복사 생성자를 제공하지 않는 게 좋음
 *
 * 불변 객체는 자유롭게 공유할 수 있으며,
 * 불변 객체끼리 내부 데이터를 공유할 수 있음
 * ex) BigInteger의 negate 메소드가 생성하는 BigInteger는 원본 내부 배열 공유
 * 
 * 객체를 만들 때 다른 불변 객체를 구성요소로 사용하면 이점이 많음
 * Map의 키와 Set의 원소로 쓰기 좋음
 *
 * 불변 객체는 그 자체로 실패 원자성을 제공함(item 76)
 *
 * =========== 불변 클래스의 단점 ===========
 * 값이 다르면 반드시 독립된 객체로 만들어야 함
 * 값 가짓수가 많다면 모든 객체를 만드는 데 비용이 큼
 * -> 다단계 연산을 예측해서 기본 기능으로 제공
 *    가변 동반 클래스 사용
 *
 * =========== 불변 클래스를 만드는 또 다른 설계 방법 ===========
 * (1. final 클래스)
 * 2. 모든 생성자를 private 혹은 package-private
 *    public 정적 팩토리 제공
 *  public class Complex {
 *    ...
 *    public static Complex valueOf(double re, double im) {
 *      return new Complex(re, im);
 *    }
 *    ...
 *  }
 *
 * ============= 정리 ==============
 * - 클래스는 꼭 필요한 경우가 아니라변 불변이어야 함
 * - 불변으로 만들 수 없는 클래스라도 변경할 수 있는 부분을 최소한으로
 * - 생성자는 불변식 설정이 모두 완료된, 초기화가 완벽히 끝난 상태의 객체를 생성
 */
public class item17 {

    /*
     * 복소수 클래스
     *
     * 함수형 프로그래밍해서 코드의 불변 영역 비율이 높아지는 장점
     * 불변 객체는 생성된 시점의 상태를 파괴될 때까지 그대로 간직
     */
    public final class Complex {
        private final double re;
        private final double im;

        public Complex(double re, double im) {
            this.re = re;
            this.im = im;
        }

        public double realPart() { return re; }
        public double imaginaryPart() { return im; }

        public Complex plus(Complex c) {
            return new Complex(re + c.re, im + c.im);
        }

        public Complex minus(Complex c) {
            return new Complex(re - c.re, im - c.im);
        }

        public Complex times(Complex c) {
            return new Complex(re * c.re - im * c.im,
                    re * c.im + im * c.re);
        }

        public Complex dividedBy(Complex c) {
            double tmp = c.re * c.re + c.im * c.im;
            return new Complex((re * c.re + im * c.im) / tmp,
                    (im * c.re - re * c.im) / tmp);
        }

        @Override
        public boolean equals(Object o) {
            if(o == this)
                return true;
            if(!(o instanceof Complex))
                return false;
            Complex c = (Complex) o;

            return Double.compare(c.re, re) == 0
                    && Double.compare(c.im, im) == 0;
        }

        @Override
        public int hashCode() {
            return 31 * Double.hashCode(re) + Double.hashCode(im);
        }

        @Override
        public String toString() {
            return "(" + re + " + " + im + "i)";
        }
    }
}
