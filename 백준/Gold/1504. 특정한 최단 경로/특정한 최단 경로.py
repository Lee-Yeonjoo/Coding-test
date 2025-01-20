import sys
import heapq
input = sys.stdin.readline
INF = int(1e9)

N, E = map(int, input().split())
graph = [[] for _ in range(N+1)]

for _ in range(E):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

v1, v2 = map(int, input().split())

#다익스트라
def dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0

    while q:
        dist, now = heapq.heappop(q)

        if distance[now] < dist:
            continue

        for i in graph[now]:
            cost = dist + i[1]

            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q, (cost, i[0]))

answer1 = 0
node = [1, v1, v2, N]
#1~v1까지의 최단거리 + v1~v2까지의 최단거리 + v2~N까지의 최단거리 
for i in range(3):
    distance = [INF] * (N+1)
    dijkstra(node[i])
    answer1 += distance[node[i+1]]

answer2 = 0
node = [1, v2, v1, N]
for i in range(3):
    distance = [INF] * (N+1)
    dijkstra(node[i])
    answer2 += distance[node[i+1]]
    
if answer1 >= INF and answer2 >= INF:
    print(-1)
elif answer1 <= answer2:
    print(answer1)
else:
    print(answer2)