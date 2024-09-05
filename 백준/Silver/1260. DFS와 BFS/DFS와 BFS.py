# DFS & BFS
from collections import deque
import sys
input = sys.stdin.readline

N, M, V = map(int, input().split())

graph = [[] for _ in range(N+1)]  # 0번 행은 쓰지 않음. 빈 2차원 리스트는 리스트 컴프리헨션을 통해 선언
for i in range(M):
    x, y = map(int, input().split())
    graph[x].append(y)
    graph[y].append(x)

for i in range(1, N+1):
    graph[i].sort()  # 작은 번호부터 방문해야하므로 정렬

# DFS 구현
visited = [False] * (N+1)

def dfs(graph, visited, v):  # v는 방문할 현재 노드
    visited[v] = True  # 현재 노드 방문 처리

    print(v, end=" ")

    for i in graph[v]:
        if visited[i] == False:
            dfs(graph, visited, i)

dfs(graph, visited, V)
print()

# BFS 구현
del visited
visited = [False] * (N+1)

queue = deque([V])   # 시작 노드와 함께 큐 생성
visited[V] = True  # 시작 노드 방문 처리

while queue:
    v = queue.popleft()  # 현재 노드
    print(v, end=" ")

    for i in graph[v]:
        if visited[i] == False:
            queue.append(i)
            visited[i] = True