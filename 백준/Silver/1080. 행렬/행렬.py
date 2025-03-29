import sys
input = sys.stdin.readline

N, M = map(int, input().split())
A = []
B = []

for _ in range(N):
    A.append(list(map(int, input().rstrip())))

for _ in range(N):
    B.append(list(map(int, input().rstrip())))

count = 0  #뒤집을 때마다 카운트 

# 3x3씩 탐색
for i in range(0, N-2):
    for j in range(0, M-2):
        # 왼쪽 위 칸이 다르면 뒤집기 - 이후 왼쪽 위칸은 확정됨  
        if A[i][j] != B[i][j]:
            count += 1
            for r in range(3):
                for c in range(3):
                    if (A[i+r][j+c] == 0):
                        A[i+r][j+c] = 1
                    else:
                        A[i+r][j+c] = 0

if A != B:
    print(-1)
else:   
    print(count)