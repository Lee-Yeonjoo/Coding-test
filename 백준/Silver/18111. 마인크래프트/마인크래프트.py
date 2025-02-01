import sys
from collections import Counter
input = sys.stdin.readline
INF = 1e9

N, M, B = map(int, input().split())
ground = [int(x) for _ in range(N) for x in input().split()]  #Counter함수를 쓰기 위해 1차원 배열로 입력받기
height_count = Counter(ground)  #각 높이의 개수를 세줌
min_height = min(height_count)
max_height = max(height_count)

time = INF
height = 0
for h in range(min_height, max_height+1):
    count = 0
    block = B
    install = 0
    remove = 0
    for curr_h, curr_h_count in height_count.items():
        #블럭을 설치해야하는 경우
        if h > curr_h:
            install += (h - curr_h) * curr_h_count  #Counter를 통해 같은 높이에 해당하는 칸이 여러 개면 한번에 처리하니까 시간 단축 가능
        
        #블럭을 제거해야하는 경우
        if h < curr_h:
            remove += (curr_h - h) * curr_h_count
    
    block += remove  #깎은 만큼 블록의 수 증가
    count += 2*remove  #2초니까 2곱하기
    if install <= block:
        count += install
    else:  #설치할 블록의 수가 모자라므로 continue
        continue

    if time >= count:
        time = count
        if height < h:
            height = h

print(time, height)