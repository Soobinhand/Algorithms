package DataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 큐 ( 링크드 리스트 )
 *
 * 선입선출 큐를 링크드 리스트로 구현한다.
 *
 * =============== API ===============
 * Node 정적 중첩 클래스
 * - 우선 static 으로 선언한 이유는 중첩 클래스가 바깥 클래스 인스턴스를 참조하지 않아서 가능한 것이다.
 *   static 내부 클래스는 바깥 클래스 인스턴스의 임시적 참조를 유지하지 않는다.
 *   즉 static 내부 클래스로 선언하면 메모리 누수의 일반적인 원인을 예방할 수 있고,
 *   클래스의 각 인스턴스 당 더 적은 메모리를 사용하게 된다.
 *   static 이 아닌 멤버 클래스의 인스턴스는 바깥 클래스의 인스턴스와 암묵적으로 연결된다.
 *   왜냐하면 static 이 아닌 멤버 클래스는 바깥 인스턴스 없이는 생성할 수 없기 때문이다.
 *   내부 클래스에서 바깥 인스턴스에 접근할 일이 없다면 무조건 static 을 붙이자.
 *   아무튼 여기는 item 과 next 를 가지고 있다. 두 개가 하나의 변수라고 생각하자.
 *
 * LinkedQueue 생성자
 * - first, last, N 을 초기화해준다.
 *
 * isEmpty()
 * - 큐가 비어있는지 확인한다.
 *   first 가 가리키고 있는 것이 null 이라면 그 큐엔 아무것도 없는 것이다.
 *   왜냐면 하나라도 큐에 무언가가 들어갔다면 first 는 null 일 수가 없다.
 *   그건 enqueue 메소드에서 확인 가능하다.
 *
 * size()
 * - N을 반환한다. 이는 큐에 저장된 항목 갯수이다.
 *
 * peek()
 * - 큐가 비어있지 않다면 첫 번째로 들어온 item 을 반환한다.
 *   그것은 first.item 이다.
 *
 * enqueue( Item item )
 * - 큐에 원소를 추가하는 메소드이다. 기차표 살려고 기다리고 있다면 그 줄을 생각하면 된다.
 *   내가 방금 줄에 섰으면 나는 가장 최근의 노드이다.
 *   나는 last 노드에 들어가며 내 앞에 사람은 oldlast 가 된다.
 *   그리고 내 뒤에는 사람이 없으니 내 다음은 null 로 저장한다.
 *   근데 만약에 내가 섰는데 그 줄에 아무도 없었다면 first 가 last 즉, 처음과 끝 둘다 나다.
 *   근데 그게 아니라면 oldlast 의 다음은 나다. 즉 oldlast.next = last
 *   그리고 N을 하나 더해준다. 항목이 추가됐으니..
 *
 * dequeue()
 * - 큐에 있는 원소를 하나 제거하는 것인데, 맨 처음으로 들어온 노드를 제거한다.
 *   스택이랑 반대다. 즉 기차표 살려고 줄 선 사람 중 가장 먼저 산 사람이 젤 먼저 나가듯,
 *   큐도 그렇다.
 *   즉 맨 처음 줄 선 사람은 first 겠다. 근데 나갔으니,
 *   그 사람의 정보 first.item 을 반환하고
 *   그 맨 앞 사람은 그 뒷 사람이 된다.
 *   그게 코드로는 first = first.next 이다.
 *   그리고 한 명이 빠져나갔으니 당연히 N을 하나 줄여준다. 줄의 길이니까.
 *   그리고 이 사람이 나감으로써 큐가 비었다면, 즉 줄에 사람이 한 명도 없다면
 *   last 를 null 로 해준다. 그 이유는 로이터링 방지.
 *   구태여 현실에서 이걸 따져보자면 줄에 한명도 없다고 그 앞에 깃발을 꽂아 놓은 것이다.
 *   마트 장보러가면 계산대에 불 들어온 곳은 이미 사람이 있는 것이고
 *   불이 꺼져있으면 사람이 하나도 없다는 뜻과 비슷한 맥락이다.
 *   저 코드를 무조건 써야하는 것은 아니지만 혹시나하는 상황에 그렇게 처리를 해주는 것이다.
 *   사람이 없는데 불이 켜져있으면 혼란이 오니까.
 *   그리고 처음에 줄 섰던 사람의 정보를 반환한다.
 *
 * toString()
 * - 큐에 들어간 원소들을 문자열로 반환하는 메소드이다.
 *   이게 없으면 그냥 LinkedQueue 를 반환하면 이상한 주소가 나온다.
 *   근데 이게 있으면 우리가 넣은 원소가 출력된다.
 *   큐에 들어온 item 들을 차례로 StringBuilder 에 추가한다.
 *   그리고 그걸 문자열로 변환해서 출력한다.
 *
 * iterator()
 * - 반복자를 구현하는 메소드이다.
 *
 * LinkedIterator 중첩 클래스
 * - 반복자를 구현하여 출력에 도움을 주는 중첩 클래스이다.
 */

public class LinkedQueue<Item> implements Iterable<Item> {

    private Node<Item> first; // 가장 오래전에 추가된 노드
    private Node<Item> last; // 가장 최근에 추가된 노드
    private int N; // 큐에 저장된 항목 갯수

    private static class Node<Item>{
        Item item;
        Node<Item> next;
    }

    public LinkedQueue(){
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return N;
    }

    public Item peek(){
        if (isEmpty()) throw new NoSuchElementException();
        return first.item;
    }

    public void enqueue(Item item){
        Node<Item> oldlast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null; // 로이터링 방지
        return item;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Item item : this){
            sb.append(item).append(' ');
        }
        return sb.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item>{
        private Node<Item> current;
        public LinkedIterator(Node<Item> x){
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (isEmpty()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // 테스트
    public static void main(String[] args) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        linkedQueue.enqueue(1);
        linkedQueue.enqueue(2);
        linkedQueue.enqueue(3);
        System.out.println(linkedQueue);
        linkedQueue.dequeue();
        System.out.println(linkedQueue);
    }
}
