import sys
input = sys.stdin.readline

n = int(input().rstrip())
deongchi = []
for i in range(n):
    deongchi.append(list(map(int, input().split())))
ans = []
for i in range(n):
    count = 0
    for j in range(n):
        if deongchi[i][0] < deongchi[j][0] and deongchi[i][1] < deongchi[j][1]:
            count += 1
    ans.append(count + 1)

for i in ans:
    print(i, end=" ")