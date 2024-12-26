import sys
import heapq
input = sys.stdin.readline
INF = int(1e9)

N, M, C = map(int, input().split())

graph = [[] for _ in range(N+1)]
for _ in range(M):
    X, Y, Z = map(int, input().split())
    graph[X].append((Y, Z))

distance = [INF] * (N+1)
def dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0

    while q:
        dist, now = heapq.heappop(q)

        if dist > distance[now]:
            continue

        for adj in graph[now]:
            cost = dist + adj[1]
            if cost < distance[adj[0]]:
                distance[adj[0]] = cost
                heapq.heappush(q, (cost, adj[0]))

dijkstra(C)
count = 0
ans = 0
for i in distance:
    if i != INF:
        count += 1
        if ans < i:
            ans = i

print(count-1, ans)
