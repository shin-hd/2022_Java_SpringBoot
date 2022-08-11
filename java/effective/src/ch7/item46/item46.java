package ch7.item46;

import ch6.item34.item34;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.function.BinaryOperator.maxBy;
import static java.util.stream.Collectors.toMap;

/*
 * item46. 스트림에서는 부작용 없는 함수를 사용하라
 *
 *  스트림은 표현이 어렵고 쉽게 와닿지 않을 수 있는데,
 * 스트림은 함수형 프로그래밍에 기초한 패러다임이기 때문
 *  스트림 패러다임의 핵심
 * : 계산을 일련의 변환(transformation)으로 재구성하는 부분
 * 각 변환 단계는 가능한 한 이전 단계 결과를 받아 처리하는 순수 함수
 * 순수함수 : 오직 입력만이 결과에 영항을 주는 함수
 *
 *  스트림 패러다임을 이해하지 못한 채 API만 사용하는 경우
 * 스트림 연산 내에서 외부 상태를 수정하도록 할 수 있음
 * : 스트림 코드를 가장한 반복적 코드
 * => forEach 연산은 스트림 계산 결과를 보고할 때만 사용
 *
 *  스트림을 사용하려면 수집기(collector) 개념을 꼭 배워야 함
 * Collectors 클래스는 스트림 원소들을 객체 하나에 취합하는
 * 축소(reduction) 전략을 캡슐화한 블랙박스 객체
 * 수집기가 컬렉션을 생성하므로 collector라는 이름을 사용
 *
 *  수집기를 사용하면 스트림 원소를 쉽게 컬렉션으로 모을 수 있음
 * 수집기의 세 종류
 * - toList()
 *   리스트 반환
 * - toSet()
 *   집합 반환
 * - toCollection(collectionFactory)
 *   프로그래머가 지정한 컬렉션 타입 반환
 *
 *  Collectors의 나머지 36개 메소드의 대부분은 스트림을 맵으로 취합하는 기능
 * 스트림 각 원소는 키 하나와 값 하나에 연관되어 있으며
 * 다수의 스트림 원소가 같은 키에 연관될 수 있음
 *
 *  Collectors의 toMap 메소드 세 가지 버전
 * 1. 가장 간단한 맵 수집기인 toMap(keyMapper, valueMapper)
 * 스트림 원소를 키에 매핑하는 함수와 값에 매핑하는 함수를 인자로 받음
 * 2. 세 번째 인수로 merge 함수를 받는 toMap
 * - 어떤 키와 키에 연관된 원소들 중 하나를 골라 연관짓는 맵을 만들 때
 *   46-5
 * - 충돌이 나면 마지막 값을 취하는 수집기를 만들 때
 *   toMap(keyMapper, valueMapper, (oldVal, newVal) -> newVal)
 * 3. 네 번째 인수로 맵 팩토리를 받는 toMap
 *
 *  Collectors는 groupingBy 메소드를 제공
 * 입력으로 분류 함수(classifier)를 받고
 * 출력으로 원소들을 카테고리별로 모아 놓은 맵을 담은 수집기 반환
 *  GroupBy의 세 가지 버전
 * 1. 가장 간단한 groupingBy는 분류 함수 하나를 받아 맵을 반환
 * 반환된 맵에 담긴 값은 해당 카테고리에 속한 원소를 담은 리스트
 * words.collect(groupingBy(word -> aplhabetize(word)))
 * 2. 분류 함수와 함께 다운스트림 수집기를 명시해서
 * 리스트 외의 값을 갖는 맵을 생성하게 할 수 있음
 * toSet을 넘기면 Set을 값으로 가지는 맵,
 * toCollection을 넘기면 컬렉션을 값으로 갖는 맵,
 * counting을 넘기면 원소 개수와 매핑한 맵
 * ex) Map<String, Long> freq = words
 *      .collect(groupingBy(String::toLowerCase, counting())));
 * 3. 맵 팩토리도 지정 가능
 * 이 메소드는 점층적 인수 목록 패턴에 어긋나서
 * mapFactory 매개변수가 downStream보다 앞에 놓임
 * 맵과 그 안에 담긴 컬렉션의 타입을 모두 지정 가능
 *
 *  counting 메소드가 반환하는 수집기는 다운스트림 수집기 전용
 * Stream의 count 메소드를 직접 사용해도 같은 기능을 수행할 수 있으므로
 * collect(counting())으로 사용할 일은 전혀 없음
 * Collections에는 summing, averaging, summarizing 메소드에
 * 각각 int, long, double 스트림용으로 하나씩 존재
 * reducing, filtering, mapping, flatMapping, collectingAndThen
 * 대부분의 프로그래머는 몰라도 상관 없음
 *
 *  남은 메소드들은 Collectors에 정의되어 있지만 '수집'과는 관련이 없음
 * minBy, maxBy는 인수로 받은 비교자를 이용해
 * 스트림에서 값이 가장 작거나 큰 원소를 반환
 * Stream 인터페이스의 min과 max 메소드를 일반화한 것
 *
 *  마지막 메소드는 joining으로
 * CharSequence 인스턴스 스트림에만 적용할 수 있음
 * 1. 매개변수가 없는 joining은 단순히
 *    원소들을 연결(concatenate)하는 수집기 반환
 * 2. 인수 하나짜리 joining은 CharSequence 타입의
 *    구분문자를 매개변수로 받고 연결 부위에 이 구문문자를 삽입함
 * 3. 인수 3개짜리 joining은 구분문자에 더해 prefix, suffix도 받음
 *    ex) [ , ] => [came, saw, conquered]
 */
public class item46 {
    // 46-4
    private static final Map<String, item34.Operation> stringToEnum =
            Stream.of(item34.Operation.values()).collect(
                    toMap(Object::toString, e->e));

    public static void main(String[] args) {
        Map<String, Long> freq = new HashMap<>();

        // 46-3
        List<String> topTen = freq.keySet().stream()
                .sorted(comparing(freq::get).reversed())
                .limit(10)
                .collect(Collectors.toList());

        // 46-5
        // 앨범 스트림을 맵으로 바꾸는데,
        // 이 맵은 각 음악가와 음악가의 베스트 앨범을 짝지은 것
        Stream<Album> albums = Stream.of(new Album[5]);
        Map<Artist, Album> topHits = albums.collect(
                toMap(Album::artist, a->a, maxBy(comparing(Album::sales))));
    }

    private class Album {
        private Artist artist;
        private long sales;
        public Artist artist() {
            return this.artist;
        }
        public long sales() {
            return this.sales;
        }
    }
    private class Artist {}
}
