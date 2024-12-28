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

#Union 연산을 입력받아 각 각 수행
for i in range(e):
    a, b = map(int, input().split())
    union_parent(parent, a, b)

#각 원소가 속한 집합 출력(루트 노드를 출력)
print('각 원소가 속한 집합: ', end=" ")
for i in range(1, v+1):
    print(find_parent(parent, i), end=" ")
print()

#부모 테이블 내용 출력
print('부모 테이블: ', end=' ')
for i in range(1, v+1):
    print(parent[i], end=' ')