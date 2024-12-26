import sys
input = sys.stdin.readline

N = int(input().rstrip())

white = [[False] * 100 for _ in range(100)]

for _ in range(N):
    x, y = map(int, input().split())

    for i in range(x-1, x+9):
        for j in range(y-1, y+9):
            white[i][j] = True

count = 0
for i in range(100):
    for j in range(100):
        if white[i][j] == True:
            count += 1

print(count)