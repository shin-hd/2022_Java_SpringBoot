package ch4.item25;

/*
 * 톱레벨 클래스는 한 파일에 하나만 담아라
 *
 *  소스 파일 하나에 톱레벨 클래스를 여러 개 선언해도 컴파일러는 불평하지 않지만
 * 아무런 득도 없고 위험을 감수해야 하는 행위임
 * 한 클래스를 여러 가지로 정의할 수 있으며, 어떤 것을 사용할지는
 * 어느 소스 파일을 먼저 컴파일하냐에 따라 달라짐
 *  예를 들어, Utensil과 Dessert 클래스가 Utensil.java 파일에 정의
 * 운좋게 javac Main.java Dessert.java 명령으로 컴파일한다면
 * 컴파일러는 먼저 Main.java를 컴파일하고,
 * Utensil 참조를 만나면 Utensil.java 파일을 살펴
 * Utensil, Dessert를 찾아냄
 * 그 다음 두 번째 명령줄 인수로 넘어온 Dessert.java를 처리하려 할 때
 * 같은 클래스 정의가 이미 있음을 알게 되어 컴파일 오류
 *  javac Main.java, javac Main.java Utensil.java 명령으로 컴파일하면
 * Dessert.java 파일을 작성하기 전처럼 pancake를 출력하지만
 * javac Dessert.java Main.java 명령으로 컴파일하면 potpie를 출력
 * 이처럼 컴파일러에 어느 소스 파일을 먼저 건네느냐에 따라
 * 동작이 달라지는건 반드시 고쳐야 할 문제
 *  해결책은 단순히 톱레벨 클래스들을 다른 소스파일로 분리
 * 정 한 파일에 담소 싶다면 정적 멤버 클래스 사용
 */
public class item25 {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }
}
