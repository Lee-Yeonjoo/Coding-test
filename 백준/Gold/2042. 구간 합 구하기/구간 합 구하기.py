import sys
input = sys.stdin.readline

# 데이터의 개수 n, 변경 횟수 m, 구간 합 계산 횟수 k
n, m, k = map(int, input().split())

# arr는 데이터, tree는 바이너리 인덱스 트리
# 인덱스를 0부터 쓰는게 아닌 1부터 쓰려고 하나 크게 초기화
arr = [0] * (n+1)
tree = [0] * (n+1)

# i번째 수까지의 누적 합 계산하는 함수
def prefix_sum(i):
    result = 0
    while i > 0:
        result += tree[i]
        i -= (i & -i)  # i에서 0이 아닌 마지막 비트 수만큼 뺀다.
    
    return result

# i번째 수를 dif만큼 더하는 함수
# i번째 수를 변경하는 과정(어차피 누적합을 구하니까 특정 수로 변경하는 게 아닌 그냥 dif만큼 더하면 된다.)
# dif = 변경 후의 값 - 변경 전의 값
def update(i, dif):
    while i <= n:
        tree[i] += dif
        i += (i & -i)

# start부터 end까지의 구간 합을 계산하는 함수 - 많이 쓰인다.
def interval_sum(start, end):
    return prefix_sum(end) - prefix_sum(start-1)

# 메인 코드
for i in range(1, n+1):
    x = int(input())
    arr[i] = x
    update(i, x) # update지만 0에서 x만 더하는거니까 초기화 가능

for i in range(m + k):
    a, b, c = map(int, input().split())

    # a값은 업데이트인지 구간합 계산인지를 나타냄
    if a == 1:
        update(b, c-arr[b])  # b는 변경할 인덱스, c는 변경할 값
        arr[b] = c
    else:
        print(interval_sum(b, c)) # b는 구간에서의 start, c는 end
