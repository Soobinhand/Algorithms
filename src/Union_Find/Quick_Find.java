package Union_Find;

import java.util.Scanner;

/**
 * 유니온 파인드 구현 (퀵-파인드)
 */

public class Quick_Find {

    private final int[] id; // 컴포넌트 식별자를 저장
    private int count; // 컴포넌트 개수

    public Quick_Find(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int find(int p){
        return id[p];
    }
    public void union(int p, int q){
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) return;
        for (int i=0;i<id.length;i++){
            if (id[i] == pID) id[i] = qID;
        }
        count--;
    }

    // 테스트 클라이언트
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Quick_Find uf = new Quick_Find(N);
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
