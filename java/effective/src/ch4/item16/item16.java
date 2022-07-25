package ch4.item16;

/*
 * public 클래스에서는 public 필드가 아닌 접근자 메소드를 사용해라
 *
 * 인스턴스 필드들을 모아놓는 일 외에 아무 목적 없는 퇴보한 클래스
 * 데이터 필드에 직접 접근할 수 있으니 캡슐화 이점을 제공하지 못함
 * -> private 필드 + public getter 제공
 *    클래스 내부 표현 방식을 언제든 바꿀 수 있는 유연성
 * 
 * package-private 클래스, private 중첩 클래스라면
 * (패키지 안 / 클래스를 포함하는 외부 클래스까지만 동작하므로)
 * 데이터 필드 노출해도 문제 없음
 *
 * public 클래스의 필드가 불변이라도 직접 노출시키는건 좋은 생각이 아님
 * API를 변경하지 않으면 표현 방식을 바꿀 수 없고,
 * 필드를 읽을 때 부수 작업을 수행할 수 없기 때문
 * 대신 불변식은 보장할 수 있음
 */
public class item16 {

    public final class Time {
        private static final int HOURS_PER_DAY = 24;
        private static final int MINUTES_PER_HOUR = 60;

        public final int hour;
        public final int minute;

        public Time(int hour, int minute) {
            if(hour < 0 || hour >= HOURS_PER_DAY)
                throw new IllegalArgumentException("시간: " + hour);
            if(minute < 0 || minute >= MINUTES_PER_HOUR)
                throw new IllegalArgumentException("분: " + minute);
            this.hour = hour;
            this.minute = minute;
        }
    }
}
