import sys
input = sys.stdin.readline

K, N = map(int, input().split())

cables = []
for _ in range(K):
    cables.append(int(input().rstrip()))

#가능한 랜선 길이의 범위 설정
start = 1
end = 2**31 - 1

result = []
#이분탐색으로 최적의 랜선 길이를 탐색
while start <= end:
    mid = (start + end) // 2

    #나올 수 있는 랜선의 개수를 구한다.
    count = 0
    for c in cables:
        count += c // mid
    
    #랜선의 개수가 N보다 작으므로 랜선의 길이 범위를 작은 쪽으로
    if count < N:
        end = mid - 1
    #N과 같거나 커도 최대 길이가 아닐 수 있으니 큰 쪽으로 더 탐색
    elif count >= N:
        result.append(mid)
        start = mid + 1

result.append(1)  #result가 빈 배열이면 valueError가 날 수 있으므로 가능한 랜선의 최소 길이인 1을 넣어준다.
print(max(result))