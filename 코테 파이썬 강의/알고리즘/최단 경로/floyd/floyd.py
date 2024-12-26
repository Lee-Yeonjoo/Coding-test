import sys
input = sys.stdin.readline
INF = int(1e9)

#노드, 간선의 개수
n, m = map(int, input().split())

#2차원 리스트(인접 행렬), 무한으로 초기화
graph = [[INF] * (n+1) for _ in range(n+1)]

#자기 자신 -> 자기자신은 비용이 0
for a in range(1, n+1):
    for b in range(1, n+1):
        if a==b:
            graph[a][b] = 0

for _ in range(m):
    a, b, c = map(int, input().split())
    graph[a][b] = c

#점화식으로 플로이드 알고리즘 수행. 3중 반복
for k in range(1, n+1):
    for a in range(1, n+1):
        for b in range(1, n+1):
            graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])

for a in range(1, n+1):
    for b in range(1, n+1):
        if graph[a][b] == INF:
            print("도달x", end=" ")
        else:
            print(graph[a][b], end=" ")
    print()
