import sys
sys.setrecursionlimit(1000001)
input = sys.stdin.readline

def dfs(v):
    visited[v] = True

    for i in tree[v]:
        if visited[i] == False:
            parent[i] = v
            dfs(i)

N = int(input().rstrip())
visited = [False] * (N+1)
parent = [0] * (N+1)  # 각 노드의 부모 노드를 저장

tree = [[] for i in range(N+1)]
for i in range(N-1):
    u, v = map(int, input().split())
    tree[u].append(v)
    tree[v].append(u)

dfs(1)
for i in range(2, N+1):
    print(parent[i])