#요세푸스 문제 - 큐를 이용해야 시간초과x
import sys
from collections import deque
input = sys.stdin.readline

N, K = map(int, input().split())

queue = deque()
for i in range(1, N+1):
    queue.append(i)

k = 1
josephus = []
while queue:
    v = queue.popleft()
    if k == K:
        josephus.append(v)
        k = 1
    else:
        queue.append(v)
        k += 1

print("<", end="")
for i in range(N-1):
    print(josephus[i], end=", ")
print(josephus[N-1], end=">")