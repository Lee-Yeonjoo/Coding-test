# 문제: 두 배열의 원소 교체
import sys
input = sys.stdin.readline

N, K = map(int, input().split())

A = list(map(int, input().split()))
B = list(map(int, input().split()))

A.sort()
B.sort(reverse=True)  # 내림차순으로 정렬

for i in range(K):
    if A[i] < B[i]:
        A[i], B[i] = B[i], A[i]
    else:
        break  # 정렬을 했기 때문에 A가 크면 계속 A가 클 것이기 때문에 break해도 된다.

print(sum(A))