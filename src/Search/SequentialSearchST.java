package Search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 순차 탐색 ( 비순차 연결 리스트 기반 )
 *
 * 가장 쉽게 생각할 수 있는 심볼 테이블 자료구조는 키/값 쌍을 가진 노드로 연결 리스트를 만드는 것이다.
 * 그러한 구조를 기반으로 순차적으로 탐색할 수 있는 알고리즘이다.
 *
 * =============== API ===============
 * Node 중첩 클래스
 * - 연결 리스트 노드 데이터 구조
 *   심볼 테이블 자료 구조를 나타내기 위한 중첩 클래스다.
 *   키와 값 그리고 그 노드로 연결 리스트를 만들 것이다.
 *
 * size()
 * - 심볼 테이블의 사이즈를 반환한다.
 *   이는 N으로 구현했으며, 키와 값 쌍이 있는 노드의 개수이다.
 *
 * isEmpty()
 * - 연결 리스트가 비어있는지를 확인한다.
 *   N이 0이라면 true, 그게 아니라면 false 를 반환한다.
 *
 * contains ( Key key )
 * - 매개변수로 들어온 key 가 해당 연결 리스트에 포함이 되어있는지 확인하는 메소드이다.
 *   만약 해당 키가 있으면 true, 없으면 false 를 반환한다.
 *   get 메소드를 통해서 구현한다.
 *
 * get ( Key key )
 * - 해당 키에 대한 값을 조회하는 메소드이다.
 *   만약에 연결 리스트에 해당 키가 없다면 null 을 반환할 것이다.
 *   하지만 있다면, 순차 탐색으로 처음 노드부터 끝 노드까지 탐색을 한다.
 *   그 중에 매개변수로 들어온 key 와 동일한 것이 있다면 그 key 에 대한 value 를 반환한다.
 *
 * put ( Key key, Value value )
 * - key, value 쌍을 연결 리스트에 저장하는 메소드이다.
 *   여러가지 분기가 있다.
 *   만약 key 가 null 로 들어왔다면 예외처리를 해준다.
 *   value 가 null 로 들어왔다면 해당 key 를 삭제하며 끝낸다.
 *   이것은 key 가 null 값을 가질 수 없게 제약 사항을 걸어둔 것이다.
 *   이 코드로 인하여 어떤 key 도 null 을 값으로 가질 수 없게 된다.
 *   만약 key, value 모두 null 이 아니라면 순차 탐색을 시작한다.
 *   매개변수로 들어온 key 와 해당 연결리스트에 특정 키와 동일하다면 생성이 아니라 수정이다.
 *   그 value 를 매개변수로 들어온 value 로 바꿔주고 끝을 낸다.
 *   만약 애초에 key 가 그 연결 리스트에 없다면 새로운 노드를 추가한다.
 *
 * delete ( Key key )
 * - 해당 키 값을 지우는 메소드이다.
 *   키 값이 null 이 아니라면 새로운 delete ()로 들어간다.
 *
 * delete ( Node x, Key key )
 * - 만약 x 자체가 null 이면 없는 노드이므로 null 을 반환한다.
 *   그게 아니라면 매개변수로 들어온 key 와 x의 key 가 같은지 본다.
 *   같다면 지워줘야 한다.
 *   N을 우선 -1을 시켜준 후, 전의 링크를 x.next 로 옮겨준다. 즉 그 사이에 걸 지운 것이다.
 *   근데 만약 key.equals(x.key) 가 안되면 뒤에서부터 계속 찾아나간다. 재귀적으로 구현한다.
 *
 * Iterable<Key> keys()
 * - 큐를 구현하여 반복자를 구현한 메소드이다.
 *   큐에 노드에 있는 모든 key 를 넣는다.
 *   그 후 그 큐를 반환한다.
 */

public class SequentialSearchST<Key, Value> {

    private int N; // 연결 리스트의 크기
    private Node first; // 연결 리스트의 머리 노드

    private class Node{
        Key key;
        Value value;
        Node next;
        public Node(Key key, Value value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean contains(Key key){
        if (key == null) throw new IllegalArgumentException("해당 키 값이 없습니다.");
        return get(key) != null;
    }

    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("해당 키 값이 없습니다.");
        for (Node x = first; x != null; x = x.next){
            if (key.equals(x.key)){
                return x.value;
            }
        }
        return null;
    }

    public void put(Key key, Value value){
        if (key == null) throw new IllegalArgumentException("해당 키 값이 없습니다.");
        if (value == null){
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next){
            if (key.equals(x.key)){
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        N++;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("해당 키 값이 없습니다.");
        first = delete(first, key);
    }

    private Node delete(Node x, Key key){
        if (x == null) return null;
        if (key.equals(x.key)){
            N--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys(){
        Queue<Key> queue = new LinkedList<>();
        for (Node x = first; x != null; x= x.next){
            queue.add(x.key);
        }
        return queue;
    }

    // 테스트
    public static void main(String[] args) {
        SequentialSearchST<String, Integer> sequentialSearchST = new SequentialSearchST<>();
        sequentialSearchST.put("이철수", 19950208);
        sequentialSearchST.put("김철수", 19961221);
        sequentialSearchST.put("안철수", 19990909);
        sequentialSearchST.delete("김철수");
        for (String s : sequentialSearchST.keys()){
            System.out.println(sequentialSearchST.get(s));
        }
        System.out.println(sequentialSearchST.contains("이철"));
    }


}
