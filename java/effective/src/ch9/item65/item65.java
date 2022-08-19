package ch9.item65;

/*
 * item65. 리플렉션보다는 인터페이스를 사용하라
 *
 *  리플렉션 기능을 이용하면 프로그램에서 임의의 클래서에 접근할 수 있음
 * Class 객체가 주어지면 Constructor, Method, Field 인스턴스를 이용해서
 * 각각에 연결된 실제 생성자, 메소드, 필드를 조작할 수 있음
 * 
 *  리플렉션의 단점
 * 1. 컴파일타임 타입 검사가 주는 이점을 누릴 수 없음
 *  프로그램이 리플렉션을 써서 존재하지 않거나 접근할 수 없는 메소드를 호출하려고 하면
 *  런타임 오류가 발생
 * 2. 코드가 지저분하고 장황해짐
 * 3. 성능이 떨어짐
 *
 *  리플렉션은 아주 제한된 형태로만 사용해야 단점을 피하고 이점만 취할 수 있음
 * 컴파일타임에 이용할 수 없는 클래스를 사용해야만 하는 프로그램은
 * 적절한 인터페이스나 상위 클래스를 이용할 수 있다면
 * 인스턴스 생성에만 리플렉션을 쓰고, 참조해서 사용하면 됨
 *
 *  리플렉션을 사용할 때의 단점
 * 1. 런타임에 총 여섯 가지나 되는 예외를 던질 수 있음
 * 2. 클래스 이름만으로 인스턴스를 생성하기 위해 25줄이나 되는 코드를 작성해야 함
 * 그래도 일단 객체가 만들어지면 그 후에는 제약받지 않음
 *
 *  리플렉션은 런타임에 존재하지 않을 수 있는
 *  클래스, 메소드, 필드와의 의존성을 관리하기에 적합함
 * 버전이 여러 개 존재하는 외부 패키지에서
 * 가장 오래된 버전만을 지원하도록 컴파일한 다음,
 * 이후 버전 클래스와 메소드는 리플렉션으로 접근하는 방식
 *
 */
public class item65 {
}