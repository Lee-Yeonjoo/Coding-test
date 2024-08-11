# dfs 구현
def dfs(graph, v, visited):  # v는 방문할 현재 노드
    visited[v] = True  # 현재 노드 방문
    
    print(v, end=' ')  # 현재 노드 출력

    for i in graph[v]:  # v에 인접한 노드들을 검사
        if not visited[i]:
            dfs(graph, i, visited)  # 스택 대신 재귀함수를 이용

# 각 노드의 인접 노드 정보를 표현(2차원 리스트). 행 번호 = 노드 번호
graph = [
    [],  # 0번 노드는 없으므로 비워둔다.
    [2, 3, 8],
    [1, 7],
    [1, 4, 5],
    [3, 5],
    [3, 4],
    [7],
    [2, 6, 8],
    [1, 7]
]

# 방문여부를 기록. 인덱스 = 노드번호
visited = [False] * 9  # 0번 노드는 비워둘거라 크기를 9로 선언

# dfs 호출
dfs(graph, 1, visited)