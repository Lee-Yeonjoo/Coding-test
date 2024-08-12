import sys
input = sys.stdin.readline

def dfs(x):
    global cnt
    for i in range(k * 2):
        if link[i][0] == x:
            if not visited[link[i][1]]:
                cnt += 1
                visited[link[i][1]] = True  # 방문 처리
                dfs(link[i][1])

n = int(input().rstrip())
k = int(input().rstrip())

cnt = 0
link = []
visited = [False] * (n+1)
for i in range(k):
    a, b = map(int, input().split())
    link.append([a, b])
    link.append([b, a])

visited[1] = True
dfs(1)
print(cnt)