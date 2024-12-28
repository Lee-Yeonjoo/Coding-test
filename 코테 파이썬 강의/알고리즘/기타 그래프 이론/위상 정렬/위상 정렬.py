from collections import deque

#노드의 개수, 간선의 개수수
v, e = map(int, input().split())

#모든 노드의 진입차수를 저장. 0으로 초기화
indegree = [0] * (v+1)

#각 노드의 연결된 간선 정보를 저장
graph = [[] for i in range(v+1)]

#간선 정보 입력
for _ in range(e):
    a, b = map(int, input().split())
    graph[a].append(b)
    #b로 들어오는 노드가 있는 것이므로 진입차수 1증가
    indegree[b] += 1

#위상 정렬 함수
def topology_sort():
    #위상 정렬 결과를 저장
    result = []
    q = deque()

    #진입차수가 0인 노드를 큐에 먼저 삽입
    for i in range(1, v+1):
        if indegree[i] == 0:
            q.append(i)
    
    while q:
        now = q.popleft()
        #큐에서 꺼낸 노드 순서가 위상 정렬 순이므로 result에 저장
        result.append(now)

        #큐에서 꺼낸 노드와 연결된 노드들에 대해 간선 제거
        for i in graph[now]:
            #진입 차수 1 뺀다.
            indegree[i] -= 1
            #뺀 후에 0이 되었다면 큐에 삽입
            if indegree[i] == 0:
                q.append(i)
    
    for i in result:
        print(i, end = ' ')

topology_sort()