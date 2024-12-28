import sys
import heapq
input = sys.stdin.readline
INF = int(1e9)

N, M = map(int, input().split())

barns = [[] for _ in range(N+1)]

for _ in range(M):
    A, B, C = map(int, input().split())
    barns[A].append((B, C))
    barns[B].append((A, C))

distance = [INF] * (N+1)
def dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0

    while q:
        dist, now = heapq.heappop(q)

        if dist > distance[now]:
            continue

        for adj in barns[now]:
            cost = dist + adj[1]
            if distance[adj[0]] > cost:
                distance[adj[0]] = cost
                heapq.heappush(q, (cost, adj[0]))

dijkstra(1)
print(distance[N])