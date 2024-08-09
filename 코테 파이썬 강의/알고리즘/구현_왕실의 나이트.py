import sys
input = sys.stdin.readline

start = input()
x, y = int(start[1]), ord(start[0]) - 96  # 나이트의 시작 위치

dx = [-2, -2, -1, 1, 2, 2, 1, -1]  # 행의 경우
dy = [-1, 1, 2, 2, 1, -1, -2, -2]  # 열의 경우
# steps = [(-2, -1), (-1, -2), (1, -2), (2, -1), (2, 1), (1, 2), (-1, 2), (-2, 1)]
# 위와 같이 dx, dy 대신 2차원 배열로 한 번에 방향 벡터를 지정해줄 수도 있다.

cnt = 0
for i in range(len(dx)):
    nx = x + dx[i]
    ny = y + dy[i]

    if nx < 1 or nx > 8 or ny < 1 or ny > 8:
        continue

    cnt += 1

print(cnt)