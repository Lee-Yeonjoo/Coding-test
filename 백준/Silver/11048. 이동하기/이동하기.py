import sys
input = sys.stdin.readline

N, M = map(int, input().split())
maze = [list(map(int, input().split())) for _ in range(N)]

#맨 윗줄과 왼쪽 줄을 먼저 초기화 
for i in range(1, M):
    maze[0][i] += maze[0][i-1]

for i in range(1, N):
    maze[i][0] += maze[i-1][0]

#나머지에 대해 각 경우의 최댓값을 고른 후 자기 자신과 더한다.
for i in range(1, N):
    for j in range(1, M):
        maze[i][j] += max(maze[i-1][j], maze[i][j-1], maze[i-1][j-1])
    
print(maze[N-1][M-1])