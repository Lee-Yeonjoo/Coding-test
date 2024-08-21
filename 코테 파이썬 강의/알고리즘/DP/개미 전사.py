# bottom up 방식
import sys
input = sys.stdin.readline

N = int(input().rstrip())
food_warehouse = list(map(int, input().split()))

d = [0] * N  # 문제에서 100까지라고 했으니 100으로 선언해도 된다.
d[0] = food_warehouse[0]
d[1] = max(food_warehouse[0], food_warehouse[1])

for i in range(2, N):
    d[i] = max(d[i-1], d[i-2] + food_warehouse[i])

print(d[N-1])