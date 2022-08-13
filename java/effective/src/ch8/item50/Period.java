package ch8.item50;

import java.util.Date;

// 50-1 기간을 표현하는 클래스 - 불변식 못 지킴
// Date는 가변이라서 쉽게 불변식을 깨트릴 수 있음
public class Period {
    private final Date start;
    private final Date end;

    /**
     * @param start 시작 시각
     * @param end 종료 시각; 시작 시각보다 뒤여야 한다.
     * @throws IllegalArgumentException 시작 시각이 종료 시각보다 늦을 때 발생한다.
     * @throws NullPointerException start나 end가 null이면 발생한다.
     */
    public Period(Date start, Date end) {
        /*
        if (start.compareTo(end) > 0)
            throw new IllegalArgumentException(start + "가 " + end + "보다 늦다.");
        this.start = start;
        this.end = end;
         */
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        if (this.start.compareTo(this.end) > 0)
            throw new IllegalArgumentException(this.start + "가 " + this.end + "보다 늦다.");
    }

    public Date start() {
        return start;
    }

    public Date end() {
        return end;
    }
    
    // 이후 생략
}
