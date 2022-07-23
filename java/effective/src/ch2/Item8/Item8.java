package ch2.Item8;

import java.lang.ref.Cleaner;

/*
 * finalizer, cleaner 사용을 피하라
 * (C++의 destructor 와는 다르다)
 * 
 * 1. 즉시 수행된다는 보장 없음
 * 2. 수행 여부도 보장하지 않음
 * -> 상태를 영구적으로 수정하는 작업에서는 절대 사용 x
 * 3. 동작 중 발생한 예외는 무시되며, 작업이 남았더라도 종료됨
 * 4. 성능 문제 (가비지 컬렉터의 효율을 떨어뜨림)
 * 5. 보안 문제 (finalizer 공격)
 * 
 * -> AutoCloseable 구현하고 close 메소드 호출
 * 
 * finalizer, cleaner의 적절한 쓰임새
 * 1. 소유자가 close 호출하지 않는 것을 대비한 안전망
 * 2. 네이티브 피어와 연결된 객체
 */
public class Item8 {

    public static void main(String[] args) {
        Adult.main();
        Teenager.main();
    }

    public class Adult {
        public static void main() {
            // try-with resource 블록을 사용하면
            // 자동 청소가 필요 없다.
            try(Room myRoom = new Room(7)) {
                System.out.println("Adult");
            }
        }
    }

    public class Teenager {
        public static void main() {
            new Room(99);
            System.out.println("Teenager");
        }
    }
}

class Room implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();

    // 청소가 필요한 자원
    private static class State implements Runnable {
        int numJunkPiles; // 쓰레기 수
        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        // close 메소드나 cleaner가 호출
        @Override
        public void run() {
            System.out.println("Cleaning the room.");
            numJunkPiles = 0;
        }
    }

    // 상태. cleanable과 공유
    private final State state;

    // cleanable 객체.
    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() {
        cleanable.clean();
    }
}