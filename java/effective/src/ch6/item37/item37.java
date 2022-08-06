package ch6.item37;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/*
 * item37. ordinal 인덱싱 대신 EnumMap을 사용하라
 *
 *  배열이나 리스트에서 원소를 꺼낼 때
 * ordinal 메소드로 인덱스를 얻는다면 문제가 많음
 * 배열은 제네릭과 호환되지 않으므로 비검사 형변환을 수행해야 하고
 * 깔끔히 컴파일되지 않음
 * 배열은 각 인덱스의 의미를 모르므로 출력 결과에 직접 레이블을 달아야 함
 * 가장 심각한 문제는 정수는 열거 타입과 달리 타입 안전하지 않기 때문에
 * 정확한 정숫값을 사용한다는 것을 프로그래머가 직접 보증해줘야 한다는 점
 * 잘못된 값을 사용하면 잘못된 동작을 수행하던가 운이 좋아야 예외를 던짐
 *  이 상황에서 배열은 열거 타입 상수를 값으로 매핑하는 일을 하므로
 * Map을 사용할 수 있을 것임
 * 열거 타입을 키로 사용하도록 설계된 Map 구현체 : EnumMap
 * 더 짧고 명료하고 안전하고 성능도 비등함
 * 안전하지 않은 형변환도 사용하지 않고,
 * 맵의 키인 열거 타입이 그 자체로 출력용 문자열을 제공하므로
 * 출력 결과에 직접 레이블을 달 필요도 없음
 * 배열 인덱스를 계산하는 과정에서 오류가 날 가능성도 없음
 * EnumMap의 성능이 ordinal을 사용한 배열과 비등한 이유는
 * EnumMap 내부에서 배열을 사용하기 때문
 * EnumMap 생성자가 받는 키 타입의 Class 객체는 한정적 타입 토큰으로,
 * 런타임 제네릭 정보를 제공
 *  스트림을 사용해 맵을 관리하면 코드를 더 줄일 수 있지만
 * EnumMap만 사용했을 때와는 살짝 다르게 동작
 * EnumMap 버전은 열거 상수당 하나씩 중첩 맵을 만들지만,
 * 스트림 버전에서는 해당 열거 상수에 속하는 인스턴스가 있을 때만 만듦
 *
 * 
 * + 물질 상태 예제
 */
public class item37 {
    public static void main(String[] args) {
        // 37-2 EnumMap을 사용해 데이터와 열거 타입 매핑
        Plant[] garden = new Plant[5];
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle =
                new EnumMap<>(Plant.LifeCycle.class);
        for (Plant.LifeCycle lc : Plant.LifeCycle.values())
            plantsByLifeCycle.put(lc, new HashSet<>());
        for (Plant p : garden)
            plantsByLifeCycle.get(p.lifeCycle).add(p);
        System.out.println(plantsByLifeCycle);
        
        // 37-3 스트림을 사용한 코드
        // 가장 단순한 형태의 스트림 기반 코드
        // EnumMap이 아닌 고유한 맵 구현체를 사용해서
        // EnumMap을 써서 얻은 공간, 성능 이점이 사라짐
        System.out.println(Arrays.stream(garden)
                .collect(groupingBy(p -> p.lifeCycle)));

        // 37-4 스트림을 사용한 코드
        // groupingBy 메소드는
        // mapFactory 매개변수에 원하는 맵 구현체를 명시해 호출 가능
        System.out.println(Arrays.stream(garden)
                .collect(groupingBy(p -> p.lifeCycle,
                        () -> new EnumMap<>(Plant.LifeCycle.class), toSet())));
    }
}

class Plant {
    enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }

    final String name;
    final LifeCycle lifeCycle;

    Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override public String toString() {
        return name;
    }
}

// 37-6 EnumMap으로 데이터와 열거 타입 쌍 연결
enum Phase {
    //SOLID, LIQUID, GAS;
    SOLID, LIQUID, GAS, PLASMA;

    public enum Transition {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID),
        // 37-7 플라즈마 추가
        IONIZE(GAS, PLASMA), DEIONIZE(PLASMA, GAS);

        private final Phase from;
        private final Phase to;

        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        private static final Map<Phase, Map<Phase, Transition>>
        m = Stream.of(values()).collect(groupingBy(t -> t.from, () -> new EnumMap<>(Phase.class),
                toMap(t ->t.to, t -> t, (x, y) -> y, () -> new EnumMap<>(Phase.class))));
        
        public static Transition from(Phase from, Phase to) {
            return m.get(from).get(to);
        }
    }
}