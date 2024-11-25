import sys
from collections import deque
import copy
from itertools import combinations  #기존 코드는 재귀함수를 이용해 벽을 세웠고, 이는 순열이다. 조합 방식으로 바꾸어 시간초과 해결
input = sys.stdin.readline

N, M = map(int, input().split())
lab = [[] for i in range(N)]
result = 0

def bfs(empty):

    global result
    queue = deque()
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    temp = copy.deepcopy(lab)
    
    #0인 칸에 벽 세우기
    for i in empty:
        temp[i[0]][i[1]] = 1

    for i in range(N):
        for j in range(M):
            if temp[i][j] == 2:
                queue.append((i, j))  #바이러스가 temp에서 동시에 확산해야하므로 꼭 큐에 한번에 넣어 줘야한다!!!

    while queue:
        x, y = queue.popleft()

        for i in range(len(dx)):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < N and 0 <= ny < M:
                if temp[nx][ny] == 0:
                    temp[nx][ny] = 2
                    queue.append((nx, ny))
    
    count = 0 
    for i in range(N):
        for j in range(M):
            if temp[i][j] == 0:
                count += 1

    result = max(result, count)
    
for i in range(N):
    lab[i] = list(map(int, input().split()))

#0인 칸의 좌표 저장
empty = []
for i in range(N):
    for j in range(M):
        if lab[i][j] == 0:
            empty.append((i, j))


empty = list(combinations(empty, 3)) #조합하는 경우의 수를 저장
for i in empty:
    bfs(i)
print(result)