# 계수 정렬
array = [ 7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2]

# 데이터의 범위를 갖는 리스트 생성
count = [0] * (max(array) + 1)

# 개수 카운트 - O(N)
for i in array:
    count[i] += 1

# 개수만큼 인덱스값을 출력 - O(N+K)
for i in range(len(count)):  # K번 반복(K는 원소 중 가장 큰 값을 의미)
    for j in range(count[i]): # 전체 N번 반복
        print(i, end=" ")