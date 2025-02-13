import sys
import heapq
input = sys.stdin.readline

T = int(input().rstrip())
for _ in range(T):
    N = int(input().rstrip())

    #지원자들 성적 저장 
    applicants = []
    for _ in range(N):
        docu, interview = map(int, input().split())
        applicants.append((docu, interview))
    
    #지원자들의 서류 성적을 기준으로 정렬 
    applicants.sort()
    q = []  #합격자 리스트

    #서류 성적이 가장 높은 지원자는 바로 합격이니까 큐에 push 
    heapq.heappush(q, applicants[0][1])

    #정렬된 순으로 합격자 큐의 요소들 중 가장 면접 순위가 높은 지원자와 비교해서 순위가 더 높다면 push 
    for i in range(1, N):
        if applicants[i][1] < q[0]:
            heapq.heappush(q, applicants[i][1])

    print(len(q))        