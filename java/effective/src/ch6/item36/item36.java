package ch6.item36;

import java.util.EnumSet;
import java.util.Set;

/*
 * item36. 비트 필드 대신 EnumSet을 사용하라
 *
 *  열거한 값들이 주로 집합으로 사용될 경우,
 * 각 상수에 서로 다른 2의 거듭제곱 값을 할당한
 * 정수 열거 패턴을 사용해 왔음 : 비트 필드
 * 비트 필드를 사용하면 비트별 연산을 사용해 합집합과 교집합 같은
 * 집합 연산을 효율적으로 수행할 수 있음
 * 하지만 비트 필드는 정수 열거 상수와 같은 단점을 가지며
 * 추가적인 문제도 안고 있음
 *  비트 필드 값이 그대로 출력되면 해석하기 어려움
 * 비트 필드 하나에 녹아 있는 모든 원소를 순회하기도 까다로움
 * 그리고 최대 몇 비트가 필요한지 API 작성 시 미리 예측해서
 * 적절한 타입을 선택해야 함
 * API를 수정하지 않으면 비트 수를 더 늘릴 수 없기 때문
 *  정수 상수보다 열거 타입을 선호하는 프로그래머 중에도
 * 상수 집합을 주고받아야 할 때는 여전히 비트 필드를 사용하기도 하지만
 * 더 나은 대안으로 EnumSet 클래스가 존재함
 * EnumSet 클래스는 열거 타입 상수의 값으로 구성된 집합을
 * 효과적으로 표현해주며, Set 인터페이스를 완벽히 구현, 타입 안전,
 * 다른 어떤 Set 구현체와도 함께 사용가능
 * 하지만 EnumSet 내부는 비트 벡터로 구현됨
 * 원소가 총 64개 이하인 대부분의 경우라면 EnumSet 전체를
 * long 변수 하나로 표현하여 비트 필드에 비견된느 성능을 보여줌
 * removeAll과 retainAll 같은 대량 작업은
 * 비트를 효과적으로 처리할 수 있는 산술 연산을 써서 구현
 * 난해한 작업을 EnumSet이 다 처리해주므로
 * 비트를 직접 다를 때 겪는 오류들로부터 안전함
 * + 예제
 */
public class item36 {
    public static void main(String[] args) {
        Text text = new Text();
        // EnumSet 인터페이스를 건네는 클라이언트 코드
        text.applyStyles(EnumSet.of(Text.Style.BOLD, Text.Style.ITALIC));
    }
}

// 36-2 EnumSet
class Text {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    // 모든 클라이언트가 EnumSet을 건네리라 짐작되는 상황이라도
    // 인터페이스로 받는 게 일반적으로 좋은 습관
    public void applyStyles(Set<Style> styles) {}
}