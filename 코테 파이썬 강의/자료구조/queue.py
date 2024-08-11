# 덱 라이브러리를 이용해 큐를 구현하는게 시간복잡도가 낮다!
from collections import deque

queue = deque()

queue.append(5)  # 맨 뒤에 데이터 추가
queue.append(2)
queue.append(3)
queue.append(7)
queue.popleft()  # 맨 앞 데이터 삭제
queue.append(1)
queue.append(4)
queue.popleft()

print(queue)
queue.reverse()  # 역순으로 만들기
print(queue)