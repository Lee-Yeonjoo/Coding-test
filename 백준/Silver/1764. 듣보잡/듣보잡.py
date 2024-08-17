import sys

N, M = map(int, input().split())

names = dict()
for i in range(N):
    x = input().rstrip()
    names[x] = 1

for i in range(M):
    x = input().rstrip()
    if x in names.keys():
        names[x] += 1

result = []
for key in names.keys():
    if names[key] == 2:
        result.append(key)

result.sort()
print(len(result))
for i in result:
    print(i)