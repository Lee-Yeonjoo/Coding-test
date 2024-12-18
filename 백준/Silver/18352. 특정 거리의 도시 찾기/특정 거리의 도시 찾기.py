import sys
import heapq
input = sys.stdin.readline
INF = int(1e9)

N, M, K, X = map(int, input().split())

city = [[] for _ in range(N+1)]
for _ in range(M):
    A, B = map(int, input().split())
    city[A].append(B)

dist = [INF] * (N+1)  #각 도시의 최단거리. 매우 큰 수로 초기화.
def dijkstra(x):
    q = [] 
    heapq.heappush(q, (0, x))
    dist[x] = 0 

    while q:
        v_dist, v = heapq.heappop(q)

        if dist[v] < v_dist:  #v가 이미 처리됐으므로 continue
            continue

        for adj in city[v]:
            if dist[adj] > dist[v] + 1:
                dist[adj] = dist[v] + 1
                heapq.heappush(q, (dist[adj], adj))

dijkstra(X)
exist = 0
for i in range(len(dist)):
    if dist[i] == K:
        print(i)
        exist = 1
if exist == 0:
    print(-1)