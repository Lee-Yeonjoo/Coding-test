import sys
from collections import deque
input = sys.stdin.readline

N, K = map(int, input().split())

answer = 0
if N >= K:
    answer = N-K

else:
    graph = [0] * (K+2)
    visited = [False] * (K+2)
    queue = deque()
    queue.append(N)
    
    visited[N] = True

    while queue:
        v = queue.popleft()

        dx = [-1, 1, v]
        for i in dx:
            nx = v + i

            if nx >= 0 and nx < len(graph):
                if visited[nx] == False:
                    visited[nx] = True
                    queue.append(nx)
                    graph[nx] = graph[v] + 1
                    
    answer = graph[K]
 
print(answer)