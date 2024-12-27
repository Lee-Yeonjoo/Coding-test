#그래프를 어떻게 나타내야할지가 좀 어려웠다
import sys
import heapq
input = sys.stdin.readline
INF = int(1e9)

N, D = map(int, input().split())

#loads는 그래프, 고속도로에서는 각 노드로 갈 때의 거리가 1
loads = [[] for _ in range(D+1)]
for i in range(D):
    loads[i].append((i+1, 1))

#loads 그래프에 지름길도 추가
for _ in range(N):
    start, end, dist = map(int, input().split())
    #역주행할 수 없으므로 D를 넘어가는 건 그래프에 추가하지 x
    if start <= D and end <= D:
        loads[start].append((end, dist))

distance = [INF] * (D+1)
def dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0

    while q:
        dist, now = heapq.heappop(q)

        if dist > distance[now]:
            continue

        for adj in loads[now]:
            cost = dist + adj[1]
            if cost < distance[adj[0]]:
                distance[adj[0]] = cost
                heapq.heappush(q, (cost, adj[0]))

dijkstra(0)
print(distance[D])