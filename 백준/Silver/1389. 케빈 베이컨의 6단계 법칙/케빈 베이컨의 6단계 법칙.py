import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())

graph = [[] for _ in range(N+1)]
for _ in range(M):
    A, B = map(int, input().split())

    graph[A].append(B)
    graph[B].append(A)

#bfs로 탐색하여 관계 수를 count배열에 저장 
def bfs(x):
    q = deque()
    visited = [False] * (N+1)
    count = [0] * (N+1)

    q.append(x)
    visited[x] = True

    while q:
        v = q.popleft()
        
        for i in graph[v]:
            if visited[i] == False:
                q.append(i)
                visited[i] = True
                count[i] = count[v] + 1 

    return sum(count)

result = []
#모든 노드를 시작점으로 bfs
for i in range(1, N+1):
    result.append(bfs(i))

answer = min(result)
for i in range(N):
    if result[i] == answer:
        print(i+1)
        break