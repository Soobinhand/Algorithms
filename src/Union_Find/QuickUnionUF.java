package Union_Find;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 유니온 파인드 ( 퀵 유니온 )
 *
 * 유니온 파인드에서 퀵 파인드과 퀵 유니온이 존재하는데 이번엔 퀵 유니온에 대해서 공부한다.
 * union() 메소드의 연산 속도를 높여주는 것을 구현한다.
 * 증명된 사실이지만 퀵 유니온이 퀵 파인드보다 성능이 좋다. 하지만 모든 곳에서 그렇진 않다.
 *
 * =============== API ===============
 * QuickUnionUF ( int n ) 생성자
 * - parent 배열과 count 값을 초기화해준다.
 *
 * count()
 * - 연결된 컴포넌트 갯수를 반환한다.
 *   이해하기 쉽게 이야기하자면 가구 수다.
 *   처음엔 10가구로 시작하지만 자식이 계속 생겨서 가구 수가 점점 줄어들 수 있다.
 *
 * find ( int p )
 * - union 메소드를 위한 엄마 찾아주는 서비스이다.
 *   "너네 엄마가 누구니?" 의 질문에 답을 찾아준다.
 *   p의 엄마는 누굴까요? 이 p를 parent 배열에 넣어줬을 때 나오는 값이 부모다.
 *   근데 그게 같다? 그러면 그 p를 그냥 반환한다.
 *   다르다? 그러면 while 문으로 엄마를 찾는다. 결국 나오면 p를 그 parent[p]와 같은 값으로 둔다.
 *   즉 아이는 바뀌지 않는데 부모가 바뀐다. 좀 이상한 비유긴 하다. 도저히 이해가 잘 안돼서...
 *   너 부모 누구야? 데려와!
 *
 * connected ( int p, int q )
 * - p와 q의 엄마가 같은지 물어보는 것이다.
 *   둘이 부모 같아??? 안 같으면 같게 해 -> union
 *
 * validate( int p )
 * - p의 범위가 유효한지 판단하는 메소드이다.
 *
 * union ( int p, int q )
 * - 이게 가장 나쁜 메소드이다. 이건 p와 q의 엄마를 같게 만드는 메소드이다.
 *   부탁하면 해준다. p와 q의 부모가 같은지 다른지 먼저 확인한다.
 *   그러기 위해선 find 메소드를 사용해야 한다. -> find
 *   p와 q값을 find 메소드에 넣어서 값이 같은지 다른지 확인한다. 이것은 부모가 같은지만 보는 메소드.
 *   같으면 어차피 둘의 부모가 같은거니까 무시하고 지나간다.
 *   다르면? 바로 부모를 같게 해준다. 이것도 비유가 이상한데 암튼 그렇다.
 *   그래서 p의 부모를 q의 부모로 해준다.
 *   즉, union(4,3)를 호출하면 parent[3] = 3이고 parent[4] = 3이 된다.
 *   야 3하고 4하고 부모 같어? 아니? 그러면 같게 해. - union 메소드이다.
 *   p가 q를 따라간다.
 *   부모 같게 해!
 */

public class QuickUnionUF {

    private int[] parent; // parent[i] 는 i의 부모.
    private int count; // 컴포넌트 개수

    public QuickUnionUF(int n){
        parent = new int[n];
        count = n;
        for (int i=0; i < n; i++){
            parent[i] = i;
        }
    }

    public int count(){
        return count;
    }

    public int find(int p){
        validate(p);
        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }

    public void validate(int p){
        if (p < 0 || p >= parent.length){
            throw new IllegalArgumentException("범위를 넘어섬");
        }
    }

    public boolean connected(int p, int q){
        validate(p);
        validate(q);
        return find(p) == find(q);
    }

    public void union(int p, int q){
        validate(p);
        validate(q);
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }

    // 테스트
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        for (int i=0;i< 10;i++){
            int p = sc.nextInt();
            int q = sc.nextInt();
            if (quickUnionUF.connected(p,q)) continue;
            quickUnionUF.union(p,q);
        }
        System.out.println(Arrays.toString(quickUnionUF.parent));
        System.out.println(quickUnionUF.count());

    }
}
