package ch2.Item6;


import java.util.regex.Pattern;

/*
 * 불필요한 객체 생성을 피하라
 * -> 성능 향상
 *
 * 생성자 : 호출 시마다 새로운 객체 생성
 * 팩토리 메소드 : 객체 재사용 가능
 *
 * 박싱된 기본 타입보다는 기본 타입 사용
 * 의도치않은 오토박싱 주의
 * (참고.
 * 가능하면 원시타입 사용
 * collections 사용 시 어쩔 수 없이 참조타입 사용
 * 리플랙션 통해 메소드 호출하는 경우 참조타입 사용)
 *
 * 재사용해야한다면 객체 생성 x
 * 새로 만들어야한다면 재사용 x (item50)
 */
public class Item6 {

    // 매 실행마다 Pattern 인스턴스를 생성 
    static boolean isRomanNumeral(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    // Pattern 인스턴스를 캐싱해서 재사용
    public class RomanNumerals {
        private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

        static boolean isRomanNumeral(String s) {
            return ROMAN.matcher(s).matches();
        }
    }

}
