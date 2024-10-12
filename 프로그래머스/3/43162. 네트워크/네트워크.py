from collections import deque

def bfs(n, computers, i, j):
    queue = deque()
    queue.append((i,j))

    while queue:
        v = queue.popleft()

        if v[0] == v[1]:
            computers[v[0]][v[1]] == 0  #자기 자신이라면 미리 방문처리

        for j in range(0, n):
            if computers[v[1]][j] == 1:
                queue.append((v[1], j))
                computers[v[1]][j] = 0  #방문 처리
                
def solution(n, computers):
    answer = 0
    
    for i in range(0, n):
        for j in range(0,n):
            if computers[i][j] == 1:
                bfs(n, computers, i, j)
                answer += 1
                
    return answer