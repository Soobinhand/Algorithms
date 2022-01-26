package Search;

/**
 * 이진 탐색 ( 순차 배열 기반 )
 *
 * 자료 구조는 두 개의 배열 한 쌍을 이용한다.
 * 하나는 키의 배열이고 하나는 값의 배열이다.
 *
 * =============== API ===============
 * BinarySearchST 생성자
 * - 키, 값 배열의 초기 용량을 설정하는 생성자.
 *
 * size()
 * - 배열의 크기를 반환.
 *
 * isEmpty()
 * - 배열의 크기가 0이면 true 를 반환.
 *
 * contains ( Key key )
 * - key 값이 있으면 true 를 반환.
 *
 * get ( Key key )
 * - 애초에 배열이 비어있으면 null 을 반환함.
 *   그게 아니면 rank 메소드에 key 를 넣어서 나온 값을 i에 저장한다. (이분 탐색의 결과)
 *   i라는 위치와 매개 변수로 들어온 key 의 값이 같다면 해당 위치의 값을 반환한다.
 *   같지 않다면 없는 것이므로 null 을 반환한다.
 *
 * rank ( Key key )
 * - 매개변수 key 가 keys 배열의 몇 번째 원소에 이분탐색을 통해 어디있는지 반환한다.
 *   lo, hi 를 배열의 첫 원소와 마지막 원소를 가리키는 변수로 둔다.
 *   mid 값을 lo, hi 의 중간 값으로 둔다.
 *   이분 탐색을 통해 매개변수로 들어온 key 와 keys 배열의 mid 번째 원소와 같은지 확인한다.
 *   만약 같으면 mid 를 반환한다. 그러면 찾으려는 key 가 mid 번째에 있다는 것이다.
 *
 * put ( Key key, Value val )
 * - value 가 null 을 put 하려하면 있으면 안되는 것이라 key 를 통해 삭제한다.
 *   매개변수가 둘 다 null 이 아니라면 i에 rank(key) 즉 키의 위치
 *   를 넣어준다.
 *   그 후 key 가 애초에 배열에 있다면 value 값을 수정한다.
 *   그게 아니라면 뒤에서부터 i까지 순회하면서 값들을 뒤로 보낸다. 이게 정렬 작업이다.
 *   그 후 i 번째에 매개변수로 들어온 키와 값을 집어 넣는다.
 *
 * delete ( Key key )
 * - key 의 위치를 rank 함수를 통해 찾는다.
 *   만약 매개변수로 들어온 key 와 위치에 있는 키가 다르면 지울 게 없는 것이므로 그냥 빠져나온다.
 *   i번째 지워야 하므로 그 뒤에 있던 것을 하나씩 앞으로 당긴다.
 *   그리고 마지막으로 마지막 번호가 비워졌으니 로이터링 방지를 위해 null 로 초기화해준다.
 */

public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys; // 키 배열
    private Value[] vals; // 값 배열
    private int N; // 배열 크기

    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean contains(Key key){
        if (key == null) throw new IllegalArgumentException("키가 null");
        return get(key) != null;
    }

    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("키가 널");
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0){
            return vals[i];
        }
        else return null;
    }

    public int rank(Key key){
        if (key == null) throw new IllegalArgumentException("키가 널");
        int lo = 0;
        int hi = N-1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val){
        if (key == null) throw new IllegalArgumentException("키가 널");
        if (val == null){
            delete(key);
            return;
        }
        int i = rank(key);

        if (i < N && keys[i].compareTo(key) == 0){
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key){
        if (key == null) throw new IllegalArgumentException("키가 널");
        if (isEmpty()) return;
        int i = rank(key);
        if (i == N || keys[i].compareTo(key) != 0){
            return;
        }

        for (int j = i; j < N-1; j++){
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        N--;
        keys[N] = null;
        vals[N] = null;
    }

    // 테스트
    public static void main(String[] args) {
        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<>(3);
        binarySearchST.put("son", 4);
        binarySearchST.put("kim", 3);
        binarySearchST.put("all", 2);
        System.out.println(binarySearchST.rank("kim"));
    }
}
