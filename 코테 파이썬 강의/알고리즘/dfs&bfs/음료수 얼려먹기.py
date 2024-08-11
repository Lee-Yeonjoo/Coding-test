import sys
input = sys.stdin.readline

def dfs(x, y):
    # 얼음틀의 범위를 벗어나면 종료
    if x <= -1 or x >= N or y <= -1 or y >= M:
        return False
    
    # 해당 노드를 방문하지 않은 경우
    if graph[x][y] == 0:
        # 해당 노드를 방문 처리
        graph[x][y] = 1

        # 해당 노드의 상하좌우도 dfs - 재귀함수
        dfs(x-1, y)
        dfs(x, y-1)
        dfs(x+1, y)
        dfs(x, y+1)
        return True
    return False  # 해당 노드를 이미 방문한 경우 false

N, M = map(int, input().split())

graph = []
for i in range(N):
    graph.append(list(map(int, input().rstrip())))

result = 0  # 아이스크림의 개수를 카운트

for i in range(N):
    for j in range(M):
        if dfs(i,j) == True:  # 아이스크림이 되는 영역의 첫 시작노드인 경우 카운트를 한다.
            result += 1

print(result)