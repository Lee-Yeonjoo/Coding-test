import sys
sys.setrecursionlimit(10 ** 5)
input = sys.stdin.readline

N, M = map(int, input().split())

graph = [[] for _ in range(N+1)]
for _ in range(M):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)

cnt = 0  #연결 요소의 개수 세는 변수
visited = [False] * (N+1)
def dfs(x):
    visited[x] = True

    for i in graph[x]:
        if visited[i] == False:
            dfs(i)

for i in range(len(graph)):
    if graph[i] == []:
        cnt += 1
    elif visited[i] == False:
        dfs(i)
        cnt += 1

print(cnt-1)