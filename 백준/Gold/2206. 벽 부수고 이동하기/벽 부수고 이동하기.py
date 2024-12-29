#시간초과 안나는 풀이
import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())

grid = []
for _ in range(N):
    grid.append(list(map(int, input().rstrip())))

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
visited = [[[0] * 2 for _ in range(M)] for _ in range(N)]

def bfs():
    q = deque()
    q.append((0, 0, 0))
    visited[0][0][0] = 1
    visited[0][0][1] = 1

    while q:
        x, y, z = q.popleft()

        if x == N-1 and y == M-1:
            return visited[x][y][z]
 
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or nx >= N or ny < 0 or ny >= M:
                continue

            #벽을 만나지 않았고, 아직 방문하지 않은 경우
            if grid[nx][ny] == 0 and visited[nx][ny][z] == 0:
                #아직 벽을 안부셨으면 부순 이후에 방문 여부를 기록하는 visited[nx][ny][1]에도 같이 방문 여부(거리)를 표시한다.
                if z == 0:
                    visited[nx][ny][0] = visited[x][y][z] + 1
                    visited[nx][ny][1] = visited[x][y][z] + 1
                #4번의 간선을 체크할 때 벽이 있어서 부순 경우와 벽을 만나지 않아 아직 안 부순 경우가 있을 수 있으므로 z=1일 때 visited[nx][ny][0]을 건들면 안된다.
                else:
                    visited[nx][ny][z] = visited[x][y][z] + 1
                q.append((nx, ny, z))
            
            #벽을 만났고, 벽을 부술 수 있는 경우
            if grid[nx][ny] == 1 and z == 0:
                visited[nx][ny][1] = visited[x][y][0] + 1
                q.append((nx, ny, 1))

    return -1

print(bfs())