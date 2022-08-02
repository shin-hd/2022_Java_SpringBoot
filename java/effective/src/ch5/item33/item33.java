package ch5.item33;

/*
 * item33. 타입 안전 이종 컨테이너를 고려하라
 *
 *  제네릭은 컬렉션과 단일원소 컨테이너에도 흔히 쓰임
 * 이런 모든 쓰임에서 매개변수화되는 대상은 컨테이너 자신이라서
 * 하나의 컨테이너에서 매개변수화할 수 있는 타입 수가 제한됨
 *  하지만 더 유연한 수단이 필요할 때도 있음
 * 예를 들어 데이터베이스의 행는 임의 개수의 열을 가질 수 있는데
 * 모든 열을 타입 안전하게 이용하고 싶음
 * 이럴 때 컨테이너 대신 키를 매개변수화해서,
 * 커네이너에 값을 넣거나 뺄 때 매개변수화한 키를 같이 제공하면 됨
 * 이렇게 하면 제네릭 타입 시스템이 값의 타입이 키와 같음을 보장
 * 이런 설계 방식 : 타입 안전 이종 컨테이너 패턴
 * #Favorites 예제
 *  Map<Class<?>, Object>
 *  Map의 키가 비한정적 와일드카드 타입이면
 * 모든 키가 서로 다른 매개변수화 타입일 수 있음
 *  Map의 값이 단순히 Object 타입이라
 * 키와 값 사이의 타입 관계를 보증하지 않음
 * 자바의 타입 시스템으로는 둘의 관계를 명시할 방법이 없음
 * 하지만 프로그래머는 이 관계를 알고 있어 이점을 누릴 수 있음
 *  Map에서 꺼낸 객체의 타입은 Map의 값 타입인 Object이지만
 * T를 받도록 하고 싶으므로, Class의 cast 메소드를 사용해
 * 동적 형변환할 수 있음
 *  지금까지의 예제에는 두 가지 제약이 있음
 * 1. 악의적인 클라이언트가 Class 객체르르 로 타입으로 넘기면
 * 타입 안전성이 깨짐
 * 그냥 동적 형변한을 쓰면 됨
 * 2. 실체화 불가 타입에는 사용할 수 없다는 것임
 * 즉, String, String[]은 저장 가능해도 List<String>은 저장 불가
 * 이 제약을 슈퍼 타입 토큰으로 어느정도 유용하게 해결 가능하지만
 * 완벽하지는 않음
 *
 *  어노테이션 API는 한정적 타입 토큰을 적극적으로 사용함
 * 어노테이션을 런타임에 읽어오는 기능을 하는 getAnnotation 메소드는
 * 토큰으로 명시한 타입의 어노테이션이 대상 요소에 달려있다면 어노테이션을,
 * 없다면 null을 반환
 * 즉, 어노테이션된 요쇼는 키가 어노테이션 타입인 타입 안전 이종 컨테이너
 *
 *  Class<?> 타입의 객체를 한정적 타입 토큰을 받는 메소드에 넘기려면
 * 객체를 Class<? extends Annotation>처럼 형변환할 수 있지만
 * 이 형변환은 비검사이므로 경고 발생
 * Class 클래스는 이런 형변환을 안전하게 수행하는 asSubClass 메소드를 제공
 * asSubClass 메소드는 호출된 인스턴스 자신의 Class 객체를 인수가 명시한 클래스로 형변환
 * 성공하면 인수 클래스 객체를 반환, 실패하면 ClassCastException 던짐
 *
 */
public class item33 {
    // 33-2 타입 안전 이종 컨테이너 패턴
    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xabcd);
        f.putFavorite(Class.class, Favorites.class);

        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);

        System.out.printf("%s %x %s%n", favoriteString, favoriteInteger, favoriteClass.getName());
    }
}
