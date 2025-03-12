import sys
from collections import deque
input = sys.stdin.readline

F, S, G, U, D = map(int, input().split())

visited = [False] * (F+1)
#bfs로 S노드에서 시작해 각 노드에 대한 최단경로를 구한다.
#각 노드의 이웃노드는 1개 or 2개여서 굳이 그래프로  나타낼 필요없음!  
def bfs(s, g):
    q = deque()
    q.append((s, 0))  #큐에 노드와 cost를 추가 
    visited[s] = True

    while q:
        v, cost = q.popleft()
        
        #이 조건문은 시작노드가 바로 도착지일 수 있기 때문에 이웃을 찾는 for문 안에 넣으면 안된다. 
        if v == g:
            return cost
        
        #그래프로 나타내기 대신 바로 이웃노드를 탐색 가능 
        for adj in (v+U, v-D):    
            if adj < 1 or adj > F: 
                continue

            if visited[adj] == False:
                q.append((adj, cost + 1))
                visited[adj] = True
    #while문이 끝난거면 해당 노드로 갈 수 없는 거임 
    return "use the stairs"

print(bfs(S, G))