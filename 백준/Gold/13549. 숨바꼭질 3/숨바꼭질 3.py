import sys
from collections import deque
input = sys.stdin.readline

N, K = map(int, input().split())

result = [-1] * 200001

def bfs(N):
    queue = deque()
    dx = [2, -1, 1]

    queue.append(N)
    result[N] += 1

    while queue:
        v = queue.popleft()

        for i in dx:
            if i == 2:
                nx = v * i
            else:
                nx = v + i
            
            if nx < 0 or nx >= len(result):
                continue

            if result[nx] != -1:
                continue
            
            if i == 2:
                result[nx] = result[v]
            else:
                result[nx] = result[v] + 1

            if nx == K:
                return
            queue.append(nx)

bfs(N)
print(result[K])