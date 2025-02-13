import sys
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
    answer = 1  #서류 1등은 무조건 합격
    top = applicants[0][1]  #서류 1등의 면접 순위로 초기화 

    #정렬된 순으로 가장 높은 면접 순위와 비교해서 순위가 더 높으면 카운트
    for i in range(1, N):
        if applicants[i][1] < top:
            answer += 1
            top = applicants[i][1]

    print(answer)        