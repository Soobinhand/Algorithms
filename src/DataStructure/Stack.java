package DataStructure;

import java.util.Iterator;

/**
 * 스택 ( 기본 )
 *
 * 후입선출 스택은 마지막으로 들어간 것이 가장 먼저 나오는 LIFO 정책을 가지는 컬렉션이다.
 *
 * =============== API ===============
 * isEmpty()
 * - 스택이 비었는지 그렇지 않은지 확인해주는 메서드이다.
 *   비었다면 true 를 반환하고, 그렇지 않다면 false 를 반환한다.
 *
 * size()
 * - 스택의 크기를 반환하는 메서드이다.
 *
 * resize( int max )
 * - 크기 N <= max 인 스택을 크기 max 인 새로운 배열로 옮긴다.
 *
 * push( Item item )
 * - 우선 배열 크기가 부족한지 검사한다. 들어갈 자리가 있어햐 하니까 말이다.
 *   새로운 항목이 들어갈 자리가 있는지 스택 크기 N과 배열의 크기 stack.length 를 비교한다.
 *   만약 공간이 없으면, 즉 N과 stack.length 가 같다면 resize()를 이용해 배열의 크기를 두 배로 늘린다.
 *   이 메소드가 지나가면 스택 제일 위에 새로운 항목, 즉 item 을 삽입한다.
 *
 * pop()
 * - 스택 제일 위에 있는 항목을 제거한다.
 *   push()와 비슷하게 pop()에서는 항목을 삭제한 후 배열의 크기가 너무 크면 크기를 절반으로 줄인다.
 *   배열이 1/4 이하로 채워져 있는지 검사하는 것이 바람직하다.
 *   1/4 기준으로 검사하여 배열 크기를 절반으로 줄이면 크기 조정 후 스택의 상태가 절반은 비어있고 절반은 차 있는 상황이 된다.
 *
 * peek()
 * - 스택 제일 위에 있는 항목을 반환한다. 제거는 하지 않는다.
 *
 * iterator()
 * - 반복자를 구현하는 메서드이다.
 *   컬렉션 Iterator 는 객체를 리턴하는 iterator() 메서드를 구현해야 한다.
 *
 * ReverseArrayIterator 중첩 클래스
 * - 반복자를 구현하여 출력에 도움을 주는 중첩 클래스이다.
 *   굳이 이걸 사용하지 않아도 foreach 구문만으로도 충분하긴 하다.
 */

public class Stack<Item> implements Iterable<Item> {

    private Item[] stack = (Item[]) new Object[1]; // 스택 항목
    private int N = 0; // 항목 갯수

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    private void resize(int max){
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++){
            temp[i] = stack[i];
        }
        stack = temp;
    }

    public void push(Item item){
        if (N == stack.length) resize(2 * stack.length);
        stack[N++] = item;
    }

    public Item pop(){
        Item item = stack[--N];
        stack[N] = null;
        if (N > 0 && N == stack.length / 4) resize(stack.length / 2);
        return item;
    }

    public Item peek(){
        if (isEmpty()) throw new IllegalArgumentException("스택이 비었음");
        return stack[N-1];
    }

    public Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = N;

        @Override
        public boolean hasNext() {
            return N > 0;
        }

        @Override
        public Item next() {
            if (i == 0) throw new IllegalArgumentException("범위 넘어섬");
            return stack[--i];
        }

        @Override
        public void remove() {}
    }

    // 테스트
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        for (int a : stack) {
            System.out.print(a+" ");
        }

    }

}
