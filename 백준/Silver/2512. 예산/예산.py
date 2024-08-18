import sys
input = sys.stdin.readline

N = int(input().rstrip())
budget = list(map(int, input().split()))
M = int(input().rstrip())

# 최적의 상한액을 찾기 위해 이진 탐색
# 찾은 상한액보다 작은 예산요청은 그대로 하고, 큰 예산 요청은 상한액으로 해서 총 더한 값이 총예산보다 큰지 비교
# 상한액의 최소는 1, 최대는 max(예산요청)

budget.sort()  # 이진탐색을 위한 정렬
start = 1
end = max(budget)

while start <= end:
    mid = (start + end) // 2

    total = 0
    for x in budget:
        if x <= mid:
            total += x
        else:
            total += mid
    
    if total <= M:
        result = mid  # 상한액 기록
        start = mid + 1  # 조건을 만족하므로 상한액 올리기
    else:
        end = mid - 1  # 조건을 만족하지 않으므로 상한액 낮추기

print(result)