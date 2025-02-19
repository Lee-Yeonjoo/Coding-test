import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N, M = map(int, input().split())

friends = [[] for _ in range(N)]
for _ in range(M):
    a, b = map(int, input().split())
    friends[a].append(b)
    friends[b].append(a)

def dfs(x, count):
    global check

    #시간 단축을 위해 더 진행x
    if check:
        return

    #dfs 깊이가 4이상이면 조건에 해당 
    if count >= 4:
        check = True
        return  #이걸 해주니 시간초과가 안남 

    visited[x] = True

    for i in friends[x]:
        if visited[i] == False:
            #깊이를 증가시킨다. 
            dfs(i, count + 1)
            visited[i] = False  #노드 번호가 작은게 먼저 방문 처리되는데, 그러면 답을 못 찾을 수 있으니 백트래킹으로 방문처리를 취소해야함!! 

#모든 노드에서 dfs를 실행해 깊이를 측정            
for i in range(N):
    visited = [False] * N
    check = False
    dfs(i, 0)

    if check == True:
        print(1)
        sys.exit()

print(0)