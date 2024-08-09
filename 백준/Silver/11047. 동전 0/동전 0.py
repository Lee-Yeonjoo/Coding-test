import sys
input = sys.stdin.readline

N, K = map(int, input().split())

coin = []
for i in range(N):
    x = int(input().rstrip())
    coin.append(x)

result = 0
i = len(coin) - 1
while K != 0:
    result += (K // coin[i])  # 동전으로 나눈 몫이 개수가 됨
    K %= coin[i]  # K는 나머지 값으로 갱신
    i -= 1

print(result)