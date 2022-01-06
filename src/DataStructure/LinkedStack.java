package DataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 스택 ( 링크드 리스트 )
 *
 * 이번 스택은 링크드 리스트로 구현한다. 이는 크기가 고정된 형태의 자료구조를 사용하지 않으니
 * resize() 메소드가 필요없다. 그 대신 중첩 클래스로 Node 를 만들었다.
 * 링크드 리스트에서 스택을 구현하려면 삽입과 삭제가 맨 앞에서 이뤄져야 한다.
 * 물론 그렇지 않아도 되지만 가장 기본적인 방법은 그렇다.
 *
 * =============== API ===============
 * Node 중첩 클래스
 * - Node 는 두 개의 인스턴스 변수를 갖는다. 하나의 데이터 항목은 Item 이고
 *   다른 하나는 또 다른 노드에 대한 참조 변수이다. 노드는 중첩 클래스로서 정의한다.
 *   그리고 노드는 클라이언트에서 사용할 타입이 아니기 때문에 private 으로 선언한다.
 *   인수 없는 생성자를 호출하여 노드 객체를 만든다. 이렇게 하면 두 개의 인스턴스 변수 모두
 *   null 로 초기화된 노드 객체의 참조가 리턴된다.
 *
 * isEmpty()
 * - 노드가 하나도 없다면 first 는 null 을 가리키고 있다.
 *   N 이 0 인지 검사해도 된다.
 *
 * size()
 * - 항목의 개수를 나타내는 인스턴스 변수 N을 두고 push, pop 이 호출될 때, 증가/감소를
 *   해주며 업데이트한다. 그리고 그것을 반환한다.
 *
 * push( Item item )
 * - 새로운 항목을 push 하면 링크드 리스트의 시작 부분에 항목을 추가한다.
 *   first 가 가리키고 있던 것은 oldfirst 에 백업해두고
 *   first 에 새로운 노드를 참조시킨다. 원소를 넣고 next 참조는 oldfirst 를 가리키게 한다.
 *   second -> first 이런 식인 것이다.
 *   쉽게 이해하자면 초등학교 때, 키 순으로 줄을 서는데 계속 나보다 작은 애들이 앞에 서는 것이다.
 *   내가 제일 먼저 왔지만 제일 뒤로 간다.
 *   이 때, 스택의 크기는 중요한 것이 아니다. 고려하지 않아도 된다.
 *
 * pop()
 * - 링크드 리스트의 사작 부분에서 항목을 제거한다.
 *   우선 pop 을 할 때에 가장 중요한 것은 제거될 값을 가져오는 것이다.
 *   왜냐하면 pop 하고 난 후에는 그걸 가져올 방법이 없기 때문이다.
 *   그래서 우선 item 에 first.item 을 대입한다.
 *   그 후, first 에는 first.next 를 대입해준다.
 *   first 가 최신의 노드라고 보면 first.next 는 그 전에 들어온 놈이다.
 *   그러니 최신의 노드에 그 전에 들어온 놈을 넣어주면 최신의 놈은 없어지고 그 최신이
 *   그 전에 들어온 놈이 되는 것이다. 그러니 키가 제일 작은 학생이 아파서 조퇴를 한 것이다.
 *   그 다음으로 작은 애가 가장 앞에 있다.
 *   마지막으로 처음에 item 을 반환한다.
 *
 * peek()
 * - peek 은 pop 과 비슷한데 이는 그냥 맨 앞에 있는 놈이 뭘 가지고 있는지,
 *   즉 맨 앞에 애의 키가 몇 인지 반환만하고 조퇴는 안 하는 것이다.
 *   first.item 을 반환한다.
 *
 * iterator()
 * - 반복자를 구현하는 메서드이다.
 *   컬렉션 Iterator 는 객체를 리턴하는 iterator() 메서드를 구현해야 한다.
 *
 * LinkedIterator 중첩 클래스
 * - 반복자를 구현하여 출력에 도움을 주는 중첩 클래스이다.
 */

public class LinkedStack<Item> implements Iterable<Item> {

    private Node first; // 가장 최근에 추가된 노드
    private int N; // 항목 갯수

    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return N;
    }

    public void push(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item pop(){
        if (isEmpty()) throw new IllegalArgumentException("스택이 비어서 pop 할게 없음");
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public Item peek(){
        if (isEmpty()) throw new IllegalArgumentException("스택이 비어서 peek 할게 없음");
        return first.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item>{

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // 테스트
    public static void main(String[] args) {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.peek());
    }
}
