#dfs가 더 효율적인 것 같다.
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n = int(input().rstrip())
a, b = map(int, input().split())
m = int(input().rstrip())

family = [[] for _ in range(n+1)]
for _ in range(m):
    x, y = map(int, input().split())
    family[x].append(y)
    family[y].append(x)

visited = [0] * (n+1)
find = 0
def dfs(v):
    global find

    if v == b:
        find = 1
        return

    for i in family[v]:
        if visited[i] == 0 and find == 0:
            visited[i] = visited[v] + 1
            dfs(i)

dfs(a)
if find == 0:
    print(-1)
else:
    print(visited[b])