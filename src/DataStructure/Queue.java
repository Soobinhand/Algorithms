package DataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 큐 ( 기본 )
 *
 * 선입선출 큐는 처음으로 들어간 것이 가장 먼저 나오는 FIFO 정책을 가지는 컬렉션이다.
 *
 * =============== API ===============
 * Queue 생성자
 * - 매개변수가 없는 생성자이다. 처음에 큐를 만들면 배열과 그 항목들을 초기화해주는데 사용한다.
 *
 * isEmpty()
 * - 큐가 비었는지 확인해주는 메서드이다.
 *   비었으면 true, 아니면 false 를 반환한다.
 *
 * size()
 * - 큐의 사이즈를 반환한다.
 *
 * resize( int max )
 * - 크기 n <= max 인 큐를 크기 max 인 새로운 배열로 옮긴다.
 *   1 2 3 4 가 들어있는 큐라면 그걸 8개로 늘려줘야한다고 가정하자.
 *   그러면 1 2 3 4 0 0 0 0 으로 옮겨갈 수 있다.
 *
 * enqueue( Item item )
 * - 큐에 원소를 삽입하는 메서드이다. 우선 확인해야할 것은 바로 n이다.
 *   n이 큐의 크기와 같다면 resize()를 해줘야한다. 그게 아니라면 삽입 연산으로 간다.
 *   last 번째에 원소를 삽입할 것이다.
 *   근데 여기서 문제는 last 가 큐의 길이와 같아지는 경우이다.
 *   이 경우, last 를 다시 0으로 초기화해주는데 그 이유는 wrap-around 의 경우와 같다.
 *   즉 어쩔 수 없는 것이다. 마지막 index 가 큐의 크기를 넘어서면 그건 오류가 날 것이기 때문이다.
 *   n과 last 는 미묘하게 다르다. last 는 그냥 현재 마지막 원소를 뜻하는 것이고
 *   n은 항목의 갯수다. 큐의 길이와는 또 다르다.
 *   1 2 3 4 에서는 당연히 n과 last 가 같을 것이다.
 *   하지만 1 2 3 4 에서 1을 제거했다고 하면
 *   n은 3이지만 last 는 그대로 4이다.대신 first 가 1이겠다.
 *   추가적으로 큐의 길이는 여전히 4이다.
 *   배열로 구현했기에 그 길이는 절대 변경할 수 없다. resize()로 강제한 것 아니면.
 *
 * dequeue()
 * - 큐에 있는 원소를 삭제하는 메서드이다.
 *   우선 확인해야할 것은 비었는지 여부이다. 비었다면 예외를 던져준다.
 *   그게 아니면 삭제 연산으로 가보자.
 *   우선 이 또한 삭제한 원소를 가져올 방법이 없어서 item 에 삭제할 원소가 무엇인지 저장해둔다.
 *   그리고 큐의 first 를 null 로 변경한다. 제거했다는 뜻이다.
 *   n은 1 줄여준다.
 *   first 는 1을 더해준다.
 *   만약 first 가 큐의 길이와 같다면 아까처럼 wrap-around 를 적용한다. 고로 first 는 0.
 *   만약 n이 큐의 길이의 반의 반밖에 안된다면 사이즈를 반으로 줄여준다.
 *   메모리 관리 측면 때문이다.
 *   그리고 마지막으로 item 을 리턴한다.
 *
 * peek()
 * - 이건 항상 그렇지만 dequeue 연산에서 실제로 지우는 연산만 안하는 것이다.
 *   그냥 가져오기만 하는 것.
 *
 * iterator()
 * - 반복자를 구현하는 메소드이다.
 *   컬렉션 Iterator 는 객체를 리턴하는 iterator()를 구현해야 한다.
 *
 * QueueIterator 중첩 클래스
 * - 반복자를 구현하여 출력에 도움을 주는 중첩 클래스이다.
 */

public class Queue<Item> implements Iterable<Item>{

    private Item[] queue;
    private int n; // 큐의 항목 갯수
    private int first; // 큐의 첫번째 항목
    private int last; // 큐의 마지막 항목

    public Queue(){
        queue = (Item[]) new Object[8];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    public void resize(int max){
        assert max >= n;
        Item[] copy = (Item[]) new Object[max];
        for (int i = 0; i < n; i++){
            copy[i] = queue[(first + i) % queue.length];
        }
        queue = copy;
        first = 0;
        last = n;
    }

    public void enqueue(Item item){
        if (n == queue.length) resize(2 * queue.length);
        queue[last++] = item;
        if (last == queue.length) last = 0;
        n++;
    }

    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException();
        Item item = queue[first];
        queue[first] = null;
        n--;
        first++;
        if (first == queue.length) first = 0;
        if (n > 0 && n == queue.length/4) resize(queue.length/2);
        return item;
    }

    public Item peek(){
        if (isEmpty()) throw new NoSuchElementException();
        return queue[first];
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item>{

        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = queue[(first + i) % queue.length];
            i++;
            return item;
        }
    }

    // 테스트
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.peek());
    }
}
