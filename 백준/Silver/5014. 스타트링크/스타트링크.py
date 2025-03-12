import sys
from collections import deque
input = sys.stdin.readline

F, S, G, U, D = map(int, input().split())

graph = [[] for _ in range(F+1)]

#그래프 생성 - 범위 내라면 각 노드i에 i+U번 노드와 i-D번 노드를 연결(방향 존재).
for i in range(1, F+1):
    if i + U <= F:
        graph[i].append(i + U)
    if i - D >= 1:
        graph[i].append(i - D)

visited = [False] * (F+1)
cost = [-1] * (F+1)
#bfs로 S노드에서 시작해 각 노드에 대한 최단경로를 구한다. 
def bfs(x):
    q = deque()
    q.append(x)
    visited[x] = True
    cost[x] = 0

    while q:
        v = q.popleft()

        for adj in graph[v]:
            if visited[adj] == False:
                q.append(adj)
                cost[adj] = cost[v] + 1
                visited[adj] = True

bfs(S)
if cost[G] == -1:
    print("use the stairs")
else:
    print(cost[G])