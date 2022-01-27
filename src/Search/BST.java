package Search;

/**
 *  이진 탐색 트리
 *
 * 각 노드가 키와 그에 연관된 값을 가지고,
 * 그 키가 왼쪽 부분 트리의 키들보다는 크고,
 * 오른쪽 부분 트리의 키들보다는 작다는 제약 조건을 만족하는
 * 이진 트리이다.
 *
 * =============== API ===============
 * Node 중첩 클래스
 * - 키, 값, 왼쪽 링크, 오른쪽 링크, 부분 트리 노드 갯수를 담은 노드.
 *
 * size( Node x )
 * - 루트 노드의 모든 자식들의 크기를 더함.
 *   근데 만약 루트 노드가 null 이면 0을 반환함.
 *   아무 노드가 없다는 뜻임.
 * get ( Key key )
 * - 매개변수로 받은 키를 찾는다.
 *   재귀적으로 구현한다. 루트부터 시작한다.
 *
 * get ( Node x, key key )
 * - 뿌리부터 시작해서 매개변수로 키가 들어온다.
 *   만약 노드가 없다면 null 을 반환한다.
 *   그게 아니라면 cmp 로 이분 탐색을 시작한다.
 *   매개변수로 들어온 key 와 들어온 노드의 key 를 compareTo 로 비교한다.
 *   cmp 가 0보다 작다는 것은 매개변수 key 가 해당 노드의 키보다 작다는 것을 의미함.
 *   고로 해당 노드의 왼쪽 노드를 다시 루트 노드로 해서 부분 트리를 탐색함. 재귀적임.
 *   cmp 가 0보다 크다는 것은 매개변수 key 가 해당 노드의 키보다 크다는 것을 의미함.
 *   그래서 해당 노드의 오른쪽 노드를 다시 루트 노드로 해서 재귀적으로 탐색함.
 *   그게 아니라 cmp=0 이면 해당 노드의 값을 반환.
 *
 * put ( Key key, Value val )
 * - 키와 값을 넣을 것임.
 *   처음에 root 에서 시작한다. 재귀적으로 구현한다.
 *
 * put ( Node x, Key key, Value val )
 * - 노드가 애초에 null 값이면 거기다가 이 키와 값을 넣어준다. 동시에 N을 1로 만든다.
 *   그게 아니면 탐색을 시작한다. 넣을 곳을.
 *   get 과 마찬가지로 cmp 를 만든다.
 *   만약 cmp 가 0보다 작다면 해당 노드의 왼쪽 노드에 다시 넣는 것을 시도합니다.
 *   그게 아니면 오른쪽 노드에 넣는 것을 시도합니다.
 *   근데 만약 cmp 가 0이라면 값만 바꿔주면 된다.
 *   그리고 해당 들어간 노드의 N은 왼쪽 오른쪽 들의 사이즈를 다 합한 것으로 귀결됩니다. 본인 +1을 해주고.
 *   그리고 그 노드를 반환함.
 *
 * min()
 * - 최솟값을 찾는 메소드
 *   어디서 시작해볼까 root!
 *
 * min ( Node x )
 * - 맨 처음으로 루트 노드가 들어옴.
 *   만약 그거 왼쪽 노드가 null 이면 그 놈이 최솟값임.
 *   그게 아니라 왼쪽 노드가 존재하면 재귀적으로 왼쪽 레프트를 다시 탐색함.
 *   그리고 노드를 반환함.
 *
 * max(), max ( Node x )
 * - 생략
 *
 * select ( int k )
 * - k 번째가 누군지 찾는 메서드.
 *   결국 순위가 k인 놈을 찾는 것.
 *
 * select ( Node x, int k )
 * - 노드에서 k 번째를 찾는다.
 *   t에 왼쪽 부분 트리들의 사이즈를 집어넣어둔다.
 *   왜냐하면 k가 t랑 같으면 그게 순위가 딱 들어맞아서.
 *   만약 t가 k보다 크면 왼쪽으로 더 가야한다는 뜻이다. 그러므로 왼쪽 노드로 다시 재귀적 탐색을 진행한다.
 *   t가 k보다 작으면 오른쪽으로 더 가야한다는 뜻이다. 그러므로 오른쪽 노드로 다시 간다.
 *   근데 갈 때, 왼쪽으로 가는 것과는 다르게 k를 k-t-1로 바꾼다.
 *   그리고 t가 k랑 같으면 노드를 반환한다.
 *
 * rank ( Key key )
 * - 매개변수로 들어온 키가 어디있는지 찾는다.
 *
 * rank ( Key key, Node x )
 * - 해당 노드에서 키가 몇 번째에 있는지 알아보자.
 *   만약 노드가 없다면 0을 반환한다.
 *   cmp 로 매개변수 키와 노드의 키를 비교한다.
 *   만약 cmp 가 0보다 작다면 그 키를 들고 왼쪽 부분 트리로 간다.
 *   그 반대는 오른쪽 부분 트리로 간다.
 *   cmp 가 0이 되는 순간. 그 왼쪽에 있는 부분트리들의 사이즈를 가져오면 된다.
 *
 * deleteMin()
 * - 제일 작은 값을 지운다.
 *
 * deleteMin ( Node x )
 * - 만약에 왼쪽 노드가 없으면 오른쪽 노드를 반환한다.
 *   그게 아니면 계속 왼쪽 노드를 찾아간다. 제일 작은걸 반환해야 하니깐
 */

public class BST<Key extends Comparable<Key>, Value> {

    private Node root; // BST 의 뿌리

    private class Node{
        private Key key; // 키
        private Value val; // 연관된 값
        private Node left, right; // 부분 트리로의 링크
        private int N; // 이 노드를 뿌리로 하는 부분 트리의 노드 갯수

        public Node(Key key, Value val, int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if (x == null) return 0;
        else return x.N;
    }

    public Value get(Key key){
        return get(root, key);
    }

    private Value get(Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val){
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val){
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min(){
        return min(root).key;
    }

    private Node min(Node x){
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max(){
        return max(root).key;
    }

    private Node max(Node x){
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key select(int k){
        return select(root, k).key;
    }

    private Node select(Node x, int k){
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else return x;
    }

    public int rank(Key key){
        return rank(key, root);
    }

    private int rank(Key key, Node x){
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return rank(key, x.right);
        else return size(x.left);
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x){
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax(){
        root = deleteMax(root);
    }

    private Node deleteMax(Node x){
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 테스트
    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        bst.put("E", 1);
        bst.put("S", 2);
        bst.put("A", 3);
        bst.put("C", 4);
        bst.put("H", 5);
        bst.put("X", 6);
        bst.put("R", 7);
        bst.put("M", 8);
        System.out.println(bst.rank("A"));
    }
}