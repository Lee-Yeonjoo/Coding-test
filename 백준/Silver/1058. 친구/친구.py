#플로이드 워셜 알고리즘
import sys
input = sys.stdin.readline
INF = int(1e9)

N = int(input().rstrip())

#인접 행렬인 2차원 리스트 
graph = [[0] * N for _ in range(N)]

#입력에 대해 초기화 
for i in range(N):
    adj = input().rstrip()
    for j in range(len(adj)):
        #자기자신이면 거리가 0 그대로
        if i == j:  
            continue

        #바로 연결된 경우 거리가 1
        if adj[j] == 'Y':
            graph[i][j] = 1
        #연결되지 않은 경우 거리가 무한 
        else:
            graph[i][j] = INF

#플로이드 워셜 점화식 - 각 모든 노드에 대해 모든 노드로의 거리를 구한다. 
for k in range(N):
    for a in range(N):
        for b in range(N):
            graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])

#거리가 1, 2이면 2-친구이므로 카운트 
max_count = 0
for node in graph:
    count = 0
    for i in node:
        if i == 1 or i == 2:
            count += 1
    
    if max_count < count:
        max_count = count

print(max_count)
    