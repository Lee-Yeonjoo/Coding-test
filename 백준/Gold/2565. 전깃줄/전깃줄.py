import sys
input = sys.stdin.readline

N = int(input().rstrip())

pole = [-1] * 501
for _ in range(N):
    A, B = map(int, input().split())
    pole[A] = B

dp = [[0, 0] for _ in range(501)]

#dp 초기화를 위해 첫번째 전깃줄 찾기 
i=0
while pole[i] == -1:
   i += 1 
dp[i][0] = 0  #자신을 포함하니까 0
dp[i][1] = 1  #자신을 포함 안하니까 1 

max_num_cnt = 0  #자기 앞의 큰 수를 세는 용도 
for j in range(i+1, 501):
    #만약 전깃줄이 없는 위치면 이전값을 dp에 복사해놓고 continue 
    if pole[j] == -1:
        dp[j][0] = dp[j-1][0] 
        dp[j][1] = dp[j-1][1]
        continue

    max_num_cnt += 1

    #자신을 포함x - 이전 값 중 더 작은거에 자신을 포함 안하므로 1 더하기 
    dp[j][1] = min(dp[j-1][0], dp[j-1][1]) + 1

    #자신을 포함
    #자신의 앞에 있는 값을 순회하여 최소값 찾기 
    min_include_self = max_num_cnt  #최소값 저장 - 자신의 앞에 작은 수가 없다면 앞에 있는 수의 개수여야 함  
    count = 0
    for k in range(j-1, -1, -1):
        #전깃줄이 없는 위치는 패스 
        if pole[k] == -1:
            continue

        #자신보다 작은 수라면 최소값인지 체크 - 이때 count는 자신과 앞의 작은 수 사이에 제거될 전깃줄의 개수 
        if pole[j] > pole[k]:
            min_include_self = min(min_include_self, dp[k][0] + count)
        count += 1

    dp[j][0] = min_include_self
    
print(min(dp[500][0], dp[500][1]))