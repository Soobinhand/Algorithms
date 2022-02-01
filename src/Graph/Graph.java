package Graph;

import java.util.ArrayList;

/**
 * Graph 데이터 타입
 *
 * 그래프는 정점의 집합과 그 집합의 정점 쌍을 연결하는 간선의 모음이다.
 *
 * =============== API ===============
 * Graph 생성자
 * - 매개변수로 받은 V를 크기로 하는 인접 리스트가 만들어진다.
 *   인접 리스트를 초기화한 후, 이중 인접 리스트라 그 안의 인접 리스트도 정점의 갯수만큼 초기화해주자.
 *
 * addEdge ( int v, int w )
 * - v와 w를 연결하는 메서드.
 *   숫자 두 개가 들어오면 그 두 개를 이어주는 간선을 만든다.
 *   간선을 만든다는 것은 추상적이다. 여기선 연결이 되어있다고 한다.
 *   연결되었다는 것은 해당 인접 리스트 번호에 숫자가 들어갔다는 것이다.
 *   0번째 원소에 [1,2,3,4] 이런식으로 들어가면 0이 1,2,3,4와 연결되었다는 것이다.
 *
 * Iterable<Integer> connectedWith ( int v )
 * - v와 연결된 것을 꺼내온다.
 *   즉 v번째 리스트를 꺼낸다.
 *
 * ArrayList<ArrayList<Integer>> getArr ( )
 * - 전체 그래프를 출력한다.
 */

public class Graph {

    private final int V; // 정점 갯수
    private int E; // 간선 갯수
    private ArrayList<ArrayList<Integer>> arr; // 인접 리스트

    public Graph(int V){
        this.V = V;
        arr = new ArrayList<>(V); // 리스트 생성
        for (int i=0;i<V;i++){
            arr.add(new ArrayList<>()); // 모든 리스트를 공백으로 초기화
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v, int w){
        arr.get(v).add(w); // w를 v의 리스트에 추가
        arr.get(w).add(v); // v를 w의 리스트에 추가한다.
        E++;
    }

    public Iterable<Integer> connectedWith(int v){
        return arr.get(v);
    }

    public ArrayList<ArrayList<Integer>> getArr(){
        return arr;
    }

    // 테스트
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,3);
        graph.addEdge(0,4);
        graph.addEdge(2,1);
        System.out.println(graph.connectedWith(1));
    }
}
