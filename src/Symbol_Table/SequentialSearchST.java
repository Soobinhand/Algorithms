package Symbol_Table;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 순차 탐색 ( 비순차 연결 리스트 기반 )
 * @param <Key>
 * @param <Value>
 */

public class SequentialSearchST<Key, Value> {

    private int n; // 연결 리스트의 크기
    private Node first; // 연결 리스트의 머리 노드

    public SequentialSearchST(){}; // 기본 생성자자

    private class Node{
        // 연결 리스트 노드 데이터 구조
        Key key;
        Value value;
        Node next;
        public Node(Key key, Value value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(Key key, Value value){
        // 키 찾기
        // 이미 존재하면 값만 바꿈.
        // 존재하지 않으면 새로운 노드를 만들어 테이블의 크기를 증가시킴.
        if (key == null) throw new IllegalArgumentException("넣을 key 가 null 이다.");
        if (value == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next){
            if (key.equals(x.key)){
                x.value = value; // 탐색 성공 : 값 바꾸기
                return;
            }
        }
        first = new Node(key, value, first); // 탐색 실패 : 새로운 노드 추가
        n++; // 크기 1 더해줌.
    }

    public Value get(Key key){
        // 주어진 키를 찾아 연관된 값을 리턴
        if (key == null) throw new IllegalArgumentException("key == null 이다.");
        for (Node x = first; x != null; x = x.next){
            if (key.equals(x.key)){
                return x.value; // 탐색 성공
            }
        }
        return null; // 탐색 실패
    }

    public int size(){
        // 연결 리스트 크기
        return n;
    }

    public boolean isEmpty(){
        // 연결 리스트가 비었으면 true
        return size() == 0;
    }

    public void delete(Key key){
        if (key == null) throw new IllegalArgumentException("key 가 null 이다.");
        first = delete(first, key);
    }

    public Node delete(Node x, Key key){
        if (x == null) return null;
        if (key.equals(x.key)){
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public boolean contains(Key key){
        // 특정 키가 있으면 true 없으면 false
        if (key == null) throw new IllegalArgumentException("contains() is null");
        return get(key) != null;
    }

    public Iterable<Key> keys(){
        Queue<Key> queue = new LinkedList<>();
        for (Node x = first; x != null; x = x.next){
            queue.add(x.key);
        }
        return queue;
    }

    // 테스트 클라이언트
    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        for (int i=0;i<5;i++){
            st.put(new Scanner(System.in).next(), i);
        }
        System.out.println(st.get("son"));
    }

}
