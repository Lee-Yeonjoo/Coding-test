import sys
input = sys.stdin.readline

N, M = map(int, input().split())
money = []
for _ in range(N):
    money.append(int(input().rstrip()))

# d = [0] * (M+1)
d = [10001] * (M+1)
d[0] = 0  # 0원은 0개로 만들 수 있음.
for i in range(1, M+1):
    for j in money:
        if i-j < 0:
            continue
    
        if d[i-j] != 10001:
            d[i] = min(d[i], d[i-j]+1)

if d[M] == 10001:
    print(-1)
else:
    print(d[M])
print(d)