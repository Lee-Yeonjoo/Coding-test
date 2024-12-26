import heapq
import sys
input = sys.stdin.readline
INF = int(1e9)  #무한을 매우 큰 수로 10억 정도

#노드의 개수, 간선의 개수
n, m = map(int, input().split())

#시작 노드 번호
start = int(input())

#노드의 연결 정보를 담는 리스트
graph = [[] for _ in range(n+1)]

#최단 거리를 저장할 리스트. 무한으로 초기화
distance = [INF] * (n+1)

for _ in range(m):
    #a노드에서 b노드로 가는 비용c
    a, b, c = map(int, input().split())
    graph[a].append((b, c))

def dijkstra(start):
    q = []

    #시작노드를 큐에 넣는다. 시작노드이므로 거리는 0
    heapq.heappush(q, (0, start))
    distance[start] = 0

    #우선순위 큐가 빌 때까지 반복
    while q:
        #큐에서 최단거리가 가장 짧은 노드를 꺼내기
        dist, now = heapq.heappop(q)

        #이미 처리된 적이 있어서 distance값이 더 작으므로 무시
        if distance[now] < dist:
            continue
        
        #현재 노드와 인접한 노드의 거리를 갱신하기 위해
        for i in graph[now]:
            #현재 노드까지의 거리와 인접 노드로 가는 거리의 합
            cost = dist + i[1]
            
            if cost < distance[i[0]]:
                distance[i[0]] = cost  #더 작으므로 갱신
                heapq.heappush(q, (cost, i[0]))

dijkstra(start)


for i in range(1, n+1):
    if distance[i] == INF:
        print("도달할 수 없음", end=" ")
    else:
        print(distance[i], end=" ")