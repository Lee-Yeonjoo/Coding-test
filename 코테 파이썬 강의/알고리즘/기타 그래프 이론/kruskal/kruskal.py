import sys
input = sys.stdin.readline

#원소가 속한 집합 찾기(루트 노드 찾기)
def find_parent(parent, x):
    #루트 노드를 찾을 때까지 재귀 호출
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]

#두 원소가 속한 집합을 합치기
def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)

    if a<b:
        parent[b] = a
    else:
        parent[a] = b

#노드의 개수, 간선(Union연산)의 개수
v, e = map(int, input().split())

#부모 테이블 초기화 - 자기자신을 부모로
parent = [i for i in range(v+1)]

#모든 간선을 담을 리스트, 최종 비용 담을 변수
edges = []
result = 0

#모든 간선에 대한 정보 입력 받기
for _ in range(e):
    a, b, cost = map(int, input().split())

    #파이썬은 정렬할 때 튜플의 첫번재 요소를 기준으로 정렬하기 때문에 첫번째 요소에 비용을 넣는다.
    edges.append((cost, a, b))

#비용 순으로 정렬
edges.sort()

#간선을 하나씩 확인
for edge in edges:
    cost, a, b = edge

    #루트 노드가 다르다 -> 사이클x -> 집합에 포함시킨다.
    if find_parent(parent, a) != find_parent(parent, b):
        union_parent(parent, a, b)
        result += cost

print(result)