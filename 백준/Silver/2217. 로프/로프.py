import sys
input = sys.stdin.readline

N = int(input().rstrip())
rope = [int(input().rstrip()) for _ in range(N)]

rope.sort()
max = rope[0] * N
for i in range(1, N):
    if rope[i] == rope[i-1]:
        continue

    x = rope[i] * (N-i)
    if x > max:
        max = x

print(max)