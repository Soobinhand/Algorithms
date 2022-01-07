package Union_Find;

import java.util.Scanner;

/**
 * 유니온 파인드 ( 퀵 파인드 )
 *
 * 유니온 파인드는 그래프 알고리즘으로 두 노드가 같은 그래프에 속하는지 판별하는 알고리즘이다.
 * 유니온 파인드에는 알고리즘에 따라 퀵 파인드와 퀵 유니온이 있으며, 가중치를 줄 수도 있다.
 * 그 중 퀵 파인드를 여기서 구현할 것이다.
 * 퀵 파인드에 대한 접근 방법은 p와 q가 연결되어 있을 때, id[p]와 id[q]가 동일하다는 조건을
 * 불변조건화하는 것이다. 다르게 말하면, 같은 컴포넌트에 속한 모든 사이트는 id[]값이 모두
 * 같아야 한다. 이러한 방법을 퀵 파인드라고 한다.
 * 왜냐하면 find(p) 에서 하는 일이 id[p]를 반환하는 것으로 완료되기 때문이다.
 * 이렇게 되면 connected 메소드도 상당히 간단한 메소드가 된다.
 * union 메소드는 우선 불변 조건을 유지하기 위해 connected 메소드를 호출하고
 * true 라면 아무것도 할 필요가 없다.
 * false 라면 모든 id[]항목들에 대해서 p 또는 q와 같은 컴포넌트들을 하나의 컴포넌트로 합쳐야 한다.
 * 즉 p와 q에 해당하는 id[] 값을 하나로 통일해야 한다. 여기서 union 은 느린 작업이 될 것이다.
 * 배열 전체를 순회해야 하니까.
 *
 * =============== API ===============
 * QuickFindUF ( int N ) 생성자
 * - 퀵 파인드 UF 를 초기화하며 배열의 크기를 설정한다.
 *   당연히 N개의 원소들이 있으므로 count 는 N과 같다고 설정한다.
 *   그리고 id 배열에 해당 번호를 넣어준다.
 *   초기화 후 id = {0,1,2,3,4,,,N} 이런 식이다.
 *
 * count()
 * - 컴포넌트의 갯수를 반환한다.
 *
 * connected ( int p, int q)
 * - p와 q가 연결되어있는지 확인하는 메소드이다.
 *   id 배열에서 p번째 들어있는 숫자가 q 번재 들어있는 숫자와 같다면 true 를 반환한다.
 *
 * find( int index )
 * - id 배열에 index 번째에 무슨 숫자가 있는지 확인하고 있으면 반환한다.
 *
 * validate ( int index )
 * - index 가 배열의 크기보다 크거나 음수가 되면 예외 처리를 해준다.
 *
 * union ( int p, int q )
 * - p와 q가 연결되어있는지 확인한다.
 *   연결되어 있다면 아무 것도 안한다. 왜? 이 메소드는 연결하려고 만든 메소드니까.
 *   연결되어 있지 않다면?
 *   사실 퀵 파인드에서 연결은 배열의 값이 같으면 연결되어 있다고 본다.
 *   즉 id[1] = 1이고 id[2] = 1 이라면 연결되어 있는 것이다.
 *   그래서 반복문으로 배열을 순회하면서 pID 를 가진 id[index]를 그냥 qID로 바꿔주기만 하면 된다.
 *   어렵진 않지만 상당히 시간이 많이 소요된다고 한다. (문제의 크기가 커지면...)
 *
 */

public class QuickFindUF {

    private int[] id; // 컴포넌트 식별자를 저장함. ( 배열 인덱스는 사이트 )
    private int count; // 컴포넌트 갯수

    public QuickFindUF(int N){
        count = N;
        id = new int[N];
        for (int i=0;i<N;i++){
            id[i] = i;
        }
    }

    public int count(){
        return count;
    }

    public boolean connected (int p, int q){
        validate(p);
        validate(q);
        return find(p) == find(q);
    }

    public int find(int index){
        validate(index);
        return id[index];
    }

    public void validate(int index){
        int n = id.length;
        if (index < 0 || index >= n) throw new IllegalArgumentException(
                "index "+index+" 는 범위를 벗어납니다."
        );
    }

    public void union(int p, int q){
        validate(p);
        validate(q);
        int pID = id[p];
        int qID = id[q];
        if (pID == qID) return;
        for (int i=0;i<id.length;i++){
            if (id[i] == pID) id[i] = qID;
        }
        count--;
    }

    // 테스트
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        QuickFindUF quickFindUF = new QuickFindUF(N);
        for (int i=0;i<N;i++){
            int p = sc.nextInt();
            int q = sc.nextInt();
            if (quickFindUF.connected(p,q)) continue;
            quickFindUF.union(p,q);
            System.out.println(p+" "+q);
        }
        System.out.println(quickFindUF.count() +"개");
    }

}
