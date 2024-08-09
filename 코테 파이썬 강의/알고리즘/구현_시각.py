import sys
input = sys.stdin.readline

N = int(input())

cnt = 0
for h in range(N+1):  # h는 시
    for m in range(60):  # m은 분
        for s in range(60):  # s는 초
            if '3' in str(h) or '3' in str(m) or '3' in str(s):
            #if '3' in str(h) + str(m) + str(s) 이렇게 하면 한번에 가능(답안 코드)
                cnt += 1

print(cnt)