package Priority_Queue;

public class MaxPQ <Key extends Comparable<Key>>{
    private Key[] pq; //힙 정렬된 완전 이진 트리
    private int N = 0;

    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN+1];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    // 상향식 힙 복구의 구현

    private void swim (int k){
        while(k > 1 && less(k/2, k)){
            exch(k/2, k);
            k = k/2;
        }
    }

// 하향식 힙 복구의 구현

    private void sink(int k){
        while(2*k <= N){
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k,j)) break;
            exch(k,j);
            k = j;
        }
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
