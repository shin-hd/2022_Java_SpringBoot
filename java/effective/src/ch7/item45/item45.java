package ch7.item45;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

/*
 * item45. 스트림은 주의해서 사용하라
 *
 *  스트림 API는 다량의 데이터 처리 작업을 돕고자 추가됨
 * API가 제공하는 두 가지 추상 개념의 핵심
 * 1. 스트림은 데이터 원소의 유한 혹은 무한 시퀀스를 의미
 * 2. 스트림 파이프라인은 이 원소들로 수행하는 연산 단계를 표현하는 개념
 *
 *  스트림 파이프라인은 소스 스트림에서 시작해 종단 연산으로 끝남
 * 그 사이 하나 이상의 중간 연산이 있을 수 있음
 * 각 중단 연산은 스트림을 어떤 방식으로 변환
 * 중간 연산은 모두 한 스트림을 다른 스트림으로 변환
 * 마지막 중간 연산이 내놓는 스트림에 종단 연산이 최후의 연산
 *
 *  스트림 파이프라인은 지연 평가됨
 * 평가는 종단 연산이 호출될 때 이뤄짐
 * 종단 연산에 쓰이지 않는 데이터 원소는 계산에 쓰이지 않음
 *  스트림 API는 메소드 연쇄를 지원하는 플루언트 API
 *  기본적으로 스트림 파이프라인은 순차적으로 수행
 *  스트림 API는 다재다능해서 어떤 계산도 해낼 수 있지만,
 * 잘못 사용하면 읽기 어렵고 유지보수가 힘들어짐
 *
 *  스트림 사용 노하우 몇 가지
 * - 스트림을 과용하면 프로그램을 읽거나 유지보수하기 어려워지므로 적당히 사용
 * - 도우미 메소드는 연산에 적절한 이름을 지어주고 세부 구현을 로직 밖으로 빼내
 *   전체적인 가독성을 높여주므로 적절히 활용하는게 좋음
 * - 자바가 char용 스트림을 지원하지 않으므로
 *   char 값들을 처리할때는 스트림을 사용하지 말아야 함
 * - 스트림으로 바꿀 수 있더라도 가독성과 유지보수 측면에서 손해가 갈 수 있으므로,
 *   스트림을 사용하면 더 나아 보일 때만 사용
 * - 스트림을 반환하는 메소드 이름은 원소의 정체를 알려주는 복수 명사를 쓰기를 강력 추천
 *
 *  함수 객체로는 할 수 없지만, 코드 블록으로는 할 수 있는 일
 * - 범위 안의 지역변수를 읽고 수정할 수 있음
 * - return, break, continue 문을 사용할 수 있음
 * - 메소드 선언에 명시된 검사 예외를 던질 수 있음
 *
 * 스트림으로 처리하기 좋은 일
 * - 원소들의 시퀀스를 일관되게 변환
 * - 원소들의 시퀀스를 필터링
 * - 원소들의 시퀀스를 하나의 연산을 사용해 결합(더하기, 연결하기, 최솟값 구하기 등)
 * - 원소들의 시퀀스를 컬렉션에 모으기(공통 속성 기준)
 * - 원소들의 시퀀스에서 특정 조건을 만족하는 원소 찾기
 *
 * 스트림으로 처리하기 어려운 일
 * - 데이터가 파이프라인의 여러 단계를 통과할 때
 *   이 데이터의 각 단계에서 값들에 동시에 접근하기 어려운 경우
 *   스트림 파이프라인은 한 값을 다른 값에 매핑하고 나면 원래 값을 잃는 구조이기 때문
 *
 * 스트림과 반복 중 어느 쪽을 써야하는지 애매한 작업도 있는데,
 * 둘 다 해보고 나은 쪽을 선택하면 됨
 */
public class item45 {
    /* 45-1
    public static void main(String[] args) throws IOException {
        File dictionary = new File(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);

        Map<String, Set<String>> groups = new HashMap<>();

        try (Scanner s = new Scanner(dictionary)) {
            while (s.hasNext()) {
                String word = s.next();
                groups.computeIfAbsent(alphabetize(word),
                        unused -> new TreeSet<>()).add(word);
            }
        }
        for (Set<String> group : groups.values())
            if (group.size() >= minGroupSize)
                System.out.println(group.size() + ": " + group);
    }
     */

    /* 45-2 스트림을 과하게 사용
    public static void main(String[] args) throws IOException {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);

        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(
                            groupingBy(word -> word.chars().sorted()
                                    .collect(StringBuilder::new,
                                            (sb, c) -> sb.append((char) c),
                                            StringBuilder::append).toString()))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .map(group -> group.size() + ": " + group)
                    .forEach(System.out::println);
        }
    }
     */
    
    // 45-3 스트림을 적절히 활용
    public static void main(String[] args) throws IOException {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);

        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(groupingBy(word -> alphabetize(word)))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(group -> System.out.println(group.size() + ": " + group));
        }
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }

    static class Suit {
        public static Suit[] values() {
            return new Suit[4];
        }
    }
    static class Rank {
        public static Rank[] values() {
            return new Rank[13];
        }
    }
    class Card {
        private Suit suit;
        private Rank rank;
        Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }
    }
    // 45-4 반복 방식
    private List<Card> newDeck1() {
        List<Card> result = new ArrayList<>();
        for (Suit suit : Suit.values())
            for (Rank rank : Rank.values())
                result.add(new Card(suit, rank));
        return result;
    }

    // 45-5 스트림 방식
    private List<Card> newDeck2() {
        return Stream.of(Suit.values())
                .flatMap(suit ->
                        Stream.of(Rank.values())
                                .map(rank -> new Card(suit, rank)))
                .collect(Collectors.toList());
    }
}
