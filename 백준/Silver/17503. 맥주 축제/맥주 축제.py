import sys
import heapq
input = sys.stdin.readline

N, M, K = map(int, input().split())
beer = []
for _ in range(K):
    v, c = map(int, input().split())
    beer.append((c, v))  #도수 순으로 정렬하기 위해 c부터 저장 

beer.sort()  #튜플의 첫번째 원소를 기준으로 정렬됨 
q = []
preference = 0  #선호도 카운트용 
alcohol = 0     #최소 도수 기록용 
check = False  #조건을 만족했는지 체크용 
for i in range(K):
    #맥주 개수가 N개보다 적은 경우 
    if len(q) < N: 
        heapq.heappush(q, (beer[i][1], beer[i][0]))  #우선순위 큐에 맥주 추가 
        preference += beer[i][1]  #선호도의 합을 카운트
        if alcohol < beer[i][0]:
            alcohol = beer[i][0]  #도수가 커지면 갱신  
    
    #맥주 개수가 N개를 만족하게 된 경우 
    if len(q) == N:
        if preference < M:
            x, y = heapq.heappop(q)  #선호도가 가장 작은 요소를 pop
            preference -= x  #pop한 요소의 선호도 빼기 
        else:
            check = True  #선호도가 M 이상을 만족하므로 True 
            break

if check:
    print(alcohol)
else:
    print(-1)
