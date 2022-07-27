package ch4.item20;

import javax.sound.sampled.Clip;
import java.util.AbstractList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/*
 * 추상 클래스보다는 인터페이스를 우선하라
 *
 * 자바 8부터 두 메커니즘 모두 인스턴스 메소드를 구현 형태로 제공 가능
 *
 * 둘의 가장 큰 차이점
 * - 추상 클래스가 정의한 타입을 구현하는 클래스는
 *   반드시 추상 클래스의 하위 클래스가 됨
 * - 인터페이스가 선언한 메소드를 모두 정의하고 일반 규약을 잘 지킨 클래스는
 *   어떤 클래스를 상속했든 같은 타입 취급
 *
 * 인터페이스를 우선하는 이유
 * 1. 기존 클래스에도 손쉽게 새로운 인터페이스 구현 가능
 * 인터페이스가 요구하는 메소드 추가하고 implements 추가하면 끝
 * 반면 기존 클래스 위에 새로운 추상 클래스를 끼워넣기는 어려움
 * 새로 추가된 추상 클래스의 모든 자손이 이를 상속하게 됨
 * 2. 인터페이스는 믹스인 정의에 안성맞춤
 * 믹스인이란? 클래스가 구현할 수 있는 타입.
 * 믹스인을 구현한 클래스에 원래의 주된 타입 외에도
 * 특정 선택적 행위를 제공한다고 선언하는 효과
 * 추상 클래스로는 믹스인을 정의할 수 없음
 * (인터페이스의 혼합?)
 * 3. 인터페이스로는 계층구조가 없는 타입 프레임워크를 만들 수 있음
 * 타입을 계층적으로 정의하면 개념을 구조적으로 잘 표현 가능하지만
 * 현실에는 계층을 구분하기 어려운 개념도 존재
 * ex) 가수, 작곡가 인터페이스
 *     => 제 3의 싱어송라이터 인터페이스(가수 + 작곡가 + a)
 * 같은 구조를 클래스로 만들려면 가능 조합 전부를 정의해야 함
 * : 조합 폭발 현상
 * 4. 래퍼 클래스 관용구와 함꼐 사용하면
 *    인터페이스는 기능을 향상시키는 안전하고 걍력한 수단
 * 타입을 추상 클래스로 정의해두면 기능 추가는 상속뿐
 * 상속으로 만든 클래스는 래퍼 클래스보다 활용도 떨어지고 깨지기 쉬움
 * 5. 인터페이스 메소드 중 구현 방법이 명백한 것이 있다면,
 *    디폴트 메소드로 제공해서 프로그래머 부담 줄임
 * 대신 상속을 위한 설명을 문서화해야 함
 * 6. 인터페이스와 추상 골격 구현 클래스를 함께 제공해서 양측의 장점을 취하는 방법
 * 인터페이스로 타입 정의, 디폴트 메소드 제공
 * 골격 구현 클래스로 나머지 메소드 구현
 * : 템플릿 메소드 패턴
 * 관례상 골격 구현 클래스 이름은 인터페이스 이름 앞에 Abstract
 * 골격 구현 클래스는 추상 클래스처럼 구현을 도와주면서,
 * 추상 클래스의 심각한 제약에서는 자유로움
 * 골격 구현 작성법
 * - 다른 메소드들의 구현에 사용되는 기반 메소드 선정
 *   이 기반 메소드가 추상 메소드가 됨
 * - 기반 메소드들을 사용해 직접 구현할 수 있는 메소드를
 *   모두 디폴트 메소드로 제공
 *   단, equals와 hashCode같은 Object 메소드는 디폴트로 제공 X
 * ( 만약 기반 메소드와 디폴트 메소드만으로 된다면 골격 구현 클래스 만들 이유가 없음 )
 * - 메소드가 남아있다면, 골격 구현 클래스를 만들어서 작성
 *
 * 골격 구현은 기본적으로 상속해서 사용하는 걸 가정
 * 상속을 고려한 설계 및 문서화 지침을 따라야 함
 *
 * 단순 구현은 골격 구현의 변종으로
 * 상속을 위해 인터페이스를 구현했지만, 추상 클래스는 아님
 * 동작하는 가장 단순한 구현
 */
public class item20 {

    // 골격 구현으로 완성한 구체 클래스
    static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);

        return new AbstractList<>() {
            @Override public Integer get(int i) {
                return a[i];
            }
            @Override public Integer set(int i, Integer val) {
                int oldVal = a[i];
                a[i] = val;
                return oldVal;
            }
            @Override public int size() {
                return a.length;
            }
        };
    }

}

// 믹스인
class Song {}
interface Singer {
    Clip sing(Song s);
}
interface Songwriter {
    Song compose(int chartPosition);
}
interface SingerSongwriter extends Singer, Songwriter {
    Clip strum();
    void actSensitive();
}

// 골격 구현 클래스
abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {
    // 변경 가능 엔트리는 이 메소드 반드시 재정의
    @Override public V setValue(V value) {
        throw new UnsupportedOperationException();
    }
    // Map.Entry.equals의 일반 규약 구현
    @Override public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Map.Entry)) return false;
        Map.Entry e = (Map.Entry) o;
        return Objects.equals(e.getKey(), getKey())
                && Objects.equals(e.getValue(), getValue());
    }
    // Map.Entry.hashCode의 일반 규약 구현
    @Override public int hashCode() {
        return Objects.hashCode(getKey())
                ^ Objects.hashCode(getValue());
    }
    @Override public String toString() {
        return getKey() + " = " + getValue();
    }
}