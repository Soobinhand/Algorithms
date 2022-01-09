package Union_Find;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 유니온 파인드 ( 가중 퀵 유니온 )
 *
 * 퀵 유니온이 퀵 파인드보다 빠르다는 것은 이미 증명된 사실이다.
 * 모든 배열 항목을 순회할 필요가 없기 때문이다.
 * 그렇지만 퀵 유니온이 퀵 파인드보다 모든 경우에 있어 현격히 빠르다고 보증할 수는 없다.
 * 어떤 데이터에 대해선 퀵 파인드가 빠를 수 있다.
 * 하지만 가중 퀵 유니온 알고리즘을 사용하면 최악 상황이 발생하지 않도록 보증할 수 있다.
 * 두 번째 트리를 첫 번째 트리에 연결할 때 임의로 연결하는 게 아니라 각 트리의 크기를 기록해 두면서
 * 항상 작은 트리를 큰 트리에 연결하게 한다.
 * 이렇게 하려면 노드의 갯수를 저장할 배열과 그것을 관리할 코드를 추가해야 한다.
 *
 * =============== API ===============
 * WeightedQuickUnionUF( int n ) 생성자
 * - parent 와 size 배열 및 count 를 초기화해준다. 크기는 n.
 *
 * count()
 * - 컴포넌트 수다. 처음엔 n 크기지만 갈수록 같거나 작아진다.
 *   연결되면 -- 연산자가 들어가므로. 한 가족이 되었다는 것이다.
 *
 * find( int p )
 * - p의 엄마가 누구인지 알려주는 메소드이다.
 *   해당 설명은 퀵 유니온에서 했으므로 생략한다.
 *
 * connected ( int p, int q )
 * - 이 메소드도 생략한다.
 *
 * union( int p, int q )
 * - 상당히 중요한 메소드이다. 아까 전하고 거의 같지만 size 라는 배열이 추가되었다.
 *   size 배열을 만들어준 이유는 트리의 크기를 기록해두는 용도이다.
 *   위의 부분은 같지만 parent[i] = j인 점이 다르다.
 *   가중치를 두지 않으면 i와 j의 크기가 어떻든 간에 위의 방식으로 처리한다.
 *   하지만 가중치를 두면 달라진다. i와 j의 크기의 가중치를 비교하면서 처리된다.
 *   size 가 큰 쪽이 무조건 부모가 되는 방식이다.
 *   그리고 부모는 작은 값만큼 더 커진다.
 *   이렇게 되면 퀵 유니온과 다른 점은 깊이이다.
 *   즉 트리의 최대 높이의 값이 가중치로 뒀을 때 훨씬 작아진다.
 */

public class WeightedQuickUnionUF {

    private int[] parent; // parent[i]는 i의 부모임.
    private int[] size; // size[i]은 i에 뿌리를 둔 하위 트리의 요소 수
    private int count; // 컴포넌트 수

    public WeightedQuickUnionUF(int n){
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i=0;i<n;i++){
            parent[i] = i;
            size[i] = i;
        }
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int find(int p){
        while(p!=parent[p]){
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (size[rootP] < size[rootQ]){
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }else{
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    // 테스트
    public static void main(String[] args) {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        Scanner sc = new Scanner(System.in);
        for (int i=0;i<10;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            weightedQuickUnionUF.union(a, b);
        }
        System.out.println(Arrays.toString(weightedQuickUnionUF.parent));
        System.out.println(Arrays.toString(weightedQuickUnionUF.size));
        System.out.println(weightedQuickUnionUF.count);
    }
}
