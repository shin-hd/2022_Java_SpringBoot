package ch7.item47;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// 47-6 입력 리스트의 모든 부분리스트를 스트림으로 반환
public class SubLists {
    public static <E> Stream<List<E>> of(List<E> list) {
        // Stream.concat : 반환되는 스트림에 빈 리스트 추가
        // flatMap : 모든 프리픽스의 모든 서픽스로 구성된 하나의 스트림 생성
        return Stream.concat(Stream.of(Collections.emptyList()),
                prefixes(list).flatMap(SubLists::suffixes));
        
        /* 47-7 모든 부분리스트를 스트림으로 반환
        return IntStream.range(0, list.size())
                .mapToObj(start -> IntStream.rangeClosed(start + 1, list.size())
                        .mapToObj(end -> list.subList(start, end)))
                .flatMap(x -> x);
         */
    }

    private static <E> Stream<List<E>> prefixes(List<E> list) {
        return IntStream.rangeClosed(1, list.size())
                .mapToObj(end -> list.subList(0, end));
    }

    private static <E> Stream<List<E>> suffixes(List<E> list) {
        return IntStream.range(0, list.size())
                .mapToObj(start -> list.subList(start, list.size()));
    }
}
