import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N = int(input().rstrip())
tree = [[] for _ in range(N)]

#트리의 연결상태를 입력받아 인접리스트 형태로 나타낸다.
x = list(map(int, input().split()))
root = 0
for i in range(N):
    if x[i] == -1:
        #루트 노드의 번호를 저장. dfs돌릴 때 필요
        root = i
        continue

    tree[x[i]].append(i)

dnode = int(input().rstrip())

leaf_node = 0
def dfs(start):
    #방문 처리를 할 필요가 없음

    global leaf_node
    count = 0
    for adj in tree[start]:
        #인접노드가 제거할 노드라면 무시
        if adj == dnode:
            continue
        
        #반복할 때마다 카운트하여 인접노드의 수를 세어 리프노드인지 판단
        count += 1
        dfs(adj)

    #카운트가 0이라면 인접노드가 없는 것이므로 리프노드의 수 증가
    if count == 0:
        leaf_node += 1

#제거할 노드가 0이라면 dfs돌리지 않고 바로 0출력
if dnode == root:
    print(0)
else:
    dfs(root)
    print(leaf_node)