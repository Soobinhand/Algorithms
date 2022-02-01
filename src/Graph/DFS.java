package Graph;

/**
 * 깊이 우선 탐색 ( Depth First Search )
 *
 * 방문했다는 표시를 한다.
 * 인접한 정점 중에 방문하지 않은 정점을 재귀적으로 방문한다.
 * 이 방법을 DFS 라고 부른다.
 *
 * =============== API ===============
 * DFS ( Graph g, int start ) 생성자
 * - visited 배열을 그래프 g의 정점 갯수만큼 초기화해준다.
 *   start 점부터 dfs 를 재귀적으로 호출한다.
 *
 * dfs ( Graph g, int start )
 * - 생성자에서 호출한 dfs 함수이다.
 *   우선 start 점은 방문을 했다는 뜻으로 visited 를 true 로 바꿔준다.
 *   그리고 foreach 문을 통해 해당 start 와 연결되어있는 점들을 방문한다.
 *   방문하면서 그 안에 있는 것들의 visited 값을 확인한다.
 *   만약 false 면 아직 그 점을 돌지 않았다는 뜻이므로 dfs 함수롤 재귀적으로 호출한다.
 *   이것이 기초적인 dfs 에 대한 개념이다.
 */
public class DFS {

    private boolean[] visited; // 방문했다는 표시를 해두는 배열

    public DFS(Graph g, int start){
        visited = new boolean[g.V()];
        dfs(g, start);
    }

    private void dfs(Graph g, int start){
        visited[start] = true;
        for (int next : g.connectedWith(start)){
            if (!visited[next]){
                dfs(g, next);
            }
        }
    }

    public boolean visited(int w){
        return visited[w];
    }

}
