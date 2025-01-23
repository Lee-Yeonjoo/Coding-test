import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

def dfs(v, group):
    global x
    visited[v] = group

    for i in graph[v]:
        if visited[i] == 0:
            dfs(i, -1 * group)
        #만약 인접노드가 내 group번호와 같다면 이분그래프가 아님
        elif visited[i] == group:
            x = True
         
K = int(input().rstrip())
for _ in range(K):
    V, E = map(int, input().split())
    graph = [[] for _ in range(V+1)]
    for _ in range(E):
        u, v = map(int, input().split())
        graph[u].append(v)
        graph[v].append(u)

    visited = [0] * (V+1)
    x = False
    #끊어진 그래프가 있을 수 있으므로 방문하지 않은 모든 노드에서 dfs
    for i in range(1, V+1):
        if visited[i] == 0:
            dfs(i, 1)
    
    #끊어진 그래프여도 그 중 하나라도 이분 그래프가 아니면 NO가 나온다.
    if x:
        print("NO")
    else:
        print("YES")
            