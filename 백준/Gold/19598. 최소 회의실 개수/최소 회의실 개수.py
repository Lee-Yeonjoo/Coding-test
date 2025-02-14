import sys
import heapq
input = sys.stdin.readline

N = int(input().rstrip())

meetings = []
for _ in range(N):
    s, e = map(int, input().split())
    meetings.append((s, e))  #회의 시작 시간을 기준으로 정렬 

meetings.sort()

q = []
heapq.heappush(q, meetings[0][1])  #종료시간을 우선순위 큐에 push

#우선순위 큐로 가장 먼저 종료되는 회의 뒤에 바로바로 배정
count = 1
for i in range(1, N):
    start = meetings[i][0]
    end = meetings[i][1]

    #이미 배정된 회의 중 가장 빨리 끝나는 회의와 겹치지 않는다면
    if q[0] <= start:
        heapq.heappop(q)  #해당 회의를 큐에서 제거 -> 이제 그 다음 타임에 회의가 배정될거니까
    else:
        count += 1  #회의가 겹치므로 회의실 추가
    heapq.heappush(q, end)

print(count)