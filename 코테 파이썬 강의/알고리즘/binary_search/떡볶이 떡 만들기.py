import sys

input = sys.stdin.readline

N, M = map(int, input().split())
array = list(map(int, input().split()))

start = 0  # 0으로 설정해야함. 절단을 하면 안되는 경우도 있으니까
end = max(array)

while start <= end:
    mid = (start+end) // 2

    x = 0  # 잘린 전체 떡의 길이
    for i in range(N):
        if array[i] > mid:
            x += array[i] - mid
        
    if x == M:
        break
    elif x > M:
        start = mid + 1
    else:
        end = mid - 1

print(mid)    