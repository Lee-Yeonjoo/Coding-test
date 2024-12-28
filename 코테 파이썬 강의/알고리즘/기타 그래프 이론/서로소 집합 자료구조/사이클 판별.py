#서로소 집합을 이용해 무방향 그래프에서 사이클 판별하기
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

#사이클 존재 여부
cycle = False

for i in range(e):
    a, b = map(int, input().split())

    #루트 노드가 같은 경우 사이클 존재재
    if find_parent(parent, a) == find_parent(parent, b):
        cycle = True
        break
    #루트 노드가 다르면 합집합 연산 수행
    else:
        union_parent(parent, a, b)

if cycle:
    print("사이클이 발생했습니다.")
else:
    print("사이클이 발생하지 않았습니다.")
