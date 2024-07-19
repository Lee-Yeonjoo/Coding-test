N, x = map(int, input().split())
arr = list(map(int, input().split()))

result = []
for i in arr:
    if x > i :
        result.append(i)

for i in result:
    print(i, end=' ')