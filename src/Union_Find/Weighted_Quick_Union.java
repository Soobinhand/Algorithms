package Union_Find;

import java.util.Scanner;

/**
 * 유니온 파인드 구현 (가중 퀵-유니온)
 */

public class Weighted_Quick_Union {

    private final int[] id; // 컴포넌트 식별자를 저장
    private int[] sz; // 각 뿌리 노드가 속한 트리의 노드 개수
    private int count; // 컴포넌트 개수

    public Weighted_Quick_Union(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++){
            sz[i] = i;
        }
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int find(int p){
        while(p!=id[p]) p = id[p];
        return p;
    }
    public void union(int p, int q){
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) return;
        if (sz[pID] < sz[qID]){
            id[pID] = qID;
            sz[qID] += sz[pID];
        }
        else{
            id[qID] = pID;
            sz[pID] += sz[qID];
        }
        count--;
    }

    // 테스트 클라이언트
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Weighted_Quick_Union uf = new Weighted_Quick_Union(N);
        while(true){
            int p = sc.nextInt();
            int q = sc.nextInt();
            if (p==0&&q==0) break;
            if (uf.connected(p,q)) continue;
            uf.union(p,q);
            System.out.println(p+" "+q);
        }
        System.out.println(uf.count() + "개가 연결");
    }
}
