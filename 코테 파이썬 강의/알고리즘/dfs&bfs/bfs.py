# bfs 구현
from collections import deque  # 큐를 이용하기 위한 덱 라이브러리

def bfs(graph, start, visited):
    queue = deque([start])

    visited[start] = True  # 현재 노드 방문처리

    while queue:  # 큐가 빌 때까지 반복
        v = queue.popleft()
        print(v, end=' ')

        for i in graph[v]:  # v의 인접노드들을 전부 방문
            if not visited[i]:
                queue.append(i)
                visited[i] = True

graph = [
    [],
    [2, 3, 8],
    [1, 7],
    [1, 4, 5],
    [3, 5],
    [3, 4],
    [7],
    [2, 6, 8],
    [1, 7]
]

visited = [False] * 9

bfs(graph, 1, visited)