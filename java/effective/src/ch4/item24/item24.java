package ch4.item24;

/*
 * 멤버 클래스는 되도록 static으로 만들라
 *
 * 중첩 클래스란? 다른 클래스 안에 정의된 클래스
 * 중첩 클래스는 자신을 감싼 바깥 클래스에서만 쓰여야 하며,
 * 그 외의 쓰임새가 있다면 톱레벨 클래스로 만들어야 함
 * 중첩 클래스의 종류로는 정적 멤버 클래스, 멤버 클래스, 익명 클래스, 지역 클레스가 있음
 * 정적 멤버 클래스를 제외한 나머지는 내부 클래스에 해당
 * 1. 정적 멤버 클래스
 * 정적 멤버 클래스는 다른 클래스 안에 선언되고,
 * 바깥 클래스의 private 멤버에도 접근 가능하다는 점만 제외하고 일반 클래스와 같음
 * 정적 멤버 클래스는 다른 정적 멤버와 같은 규칙을 적용받음
 *  정적 멤버 클래스는 흔히 public 도우미 클래스로 쓰임
 * ex) Calculator.Operation.PLUS
 * 2. (비정적) 멤버 클래스
 *  정적 멤버 클래스와 비정적 멤버 클래스의 구문상 차이는
 * static 뿐이지만, 의미상 차이는 큼
 * 비정적 멤버 클래스 인스턴스는 바깥 클래스 인스턴스와 암묵적으로 연결됨
 * 그래서 비정적 멤버 클래스의 인스턴스 메소드에서 this를 사용해
 * 바깥 인스턴스 메소드를 호출하거나 바깥 인스턴스의 참조를 가져올 수 있음
 * 정규화된 this는 클래스명.this 형태로 바깥 클래스 이름을 명시하는 용법임
 * 따라서 개념상 중첩 클래스 인스턴스가 바깥 인스턴스와
 * 독립적으로 존재할 수 있다면 정적 멤버 클래스
 * 비정적 멤버 클래스는 바깥 인스턴스 없이는 생성불가능
 *  비정적 멤버 클래스 인스턴스와 바깥 인스턴스 사이 관계는
 * 멤버 클래스가 인스턴스화될 때 확립, 더 이상 변경할 수 없음
 *  비정적 멤버 클래스는 어댑터를 정의할 때 자주 쓰임
 * 즉, 어떤 클래스 인스턴스를 감싸 다른 클래스의
 * 인터페이스처럼 보이게 하는 뷰로 사용
 * 멤버 클래스에서 바깥 인스턴스에 접근할 일이 없다면
 * 무조건 static을 붙여서 정적 멤버 클래스로 만들어야 함
 * static을 생략하면바깥 인스턴스로의 외부 참조를 갖게 되어
 * 시간과 공간이 소비되고 바깥 클래스의 인스턴스를 수거하지 못할 수 있음
 * 3. private 정적 멤버 클래스
 * private 정적 멤버 클래스는 흔히
 * 바깥 클래스가 표현하는 객체의 한 부분을 나타낼 쌔 씀
 * Map 구현체는 각 키-값 쌍을 표현하는 엔트리 객체를 가지고 있는데
 * 모든 엔트리가 맵과 연관되어 있지만
 * 엔트리의 메소드는 맵을 직접 사용하지는 않음
 * 따라서 엔트리를 비정적 멤버 클래스로 표현하는 것은 낭비
 * private 정적 멤버 클래스가 알맞음
 * static을 빼먹으면 모든 엔트리가 바깥 맵으로의 참조를 가져
 * 공간, 시간 낭비
 * 4. 익명 클래스
 * 익명 클래스는 이름도 없고 바깥 클래스 멤버도 아님
 * 쓰이는 시점에 선언과 동시에 인스턴스가 생성되고 코드 어디서든 만들 수 있음
 * 그리고 오직 비정적 문맥에서 사용될 때만 바깥 클래스 인스턴스를 참조할 수 있음
 * 정적 문맥에서라도 상수 변수 이외의 정적 멤버는 가질 수 없음
 * 즉, final 기본 타입과 문자열 필드만 가질 수 있음
 *  익명 클래스는 응용하는 데 제약이 많음
 * 선언한 지역에서만 인스턴스 생성 가능
 * instanceof 검사나 클래스 이름이 필요한 작업은 수행 불가능
 * 여러 인터페이스를 구현할 수 없음
 * 인터페이스를 구현하는 동시에 다른 클래스를 상속할 수 없음
 * 익명 클래스를 사용하는 클라이언트는 익명 클래스가 상속한 멤버 외에는 호출할 수 없음
 * 익명 클래스는 표현식 중간에 등장하므로 짧아야 가독성이 좋음
 *  자바가 람다를 지원하기 전에는 즉석으로 작은 함수 객체나 처리 객체를 만드는 데
 * 익명 클래스를 주로 사용했지만 이제는 람다가 주로 사용됨
 * 익명 클래스는 정적 팩토리 메소드를 구현할 때 쓰임
 * 5. 지역 클래스
 * 지역 클래스는 가장 드물게 사용됨
 * 지역 변수를 선언할 수 있는 곳이면 실질적으로 어디든 선언할 수 있고
 * 유효 범위도 지역 변수와 같음
 * 멤버 클래스처럼 이름이 있고 반복해서 사용할 수 있음
 * 익명 클래스처럼 비정적 문맥에서 사용될 때 바깥 인스턴스 참조 가능
 * 정적 멤버는 가질 수 없고 가독성을 위해 짧게 작성
 *
 *
 *
 */
public class item24 {
}
