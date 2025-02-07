import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

k = int(input().rstrip())
sign = input().split()

temp = [0,1,2,3,4,5,6,7,8,9]
#각 0~9 숫자에 대해 자신보다 작은 그룹과 큰 그룹을 만듦 
small = []
big = []
for i in range(10):
    small.append(temp[0:i])
    big.append(temp[i+1:])

answer = []
#숫자 x에서 시작해 가능한 모든 경우를 탐색, s는 부호를 담은 배열의 인덱스, num은 최종 수열 
def dfs(x, s, num):
    visited[x] = True

    if sign[s] == "<":
        for nx in big[x]:
            if visited[nx] == True:
                continue

            if s+1 == k:  #부호가 마지막인 경우. 탐색할 다음 부호가 없음 
                answer.append(num + str(nx))
                continue
            if s+1 < k:  #탐색할 다음 부호가 존재 
                dfs(nx, s+1, num + str(nx))
                visited[nx] = False  #백트래킹 -> dfs가 끝나면 다시 방문 안한 걸로 변경. 다른 수를 탐색할 때 영향을 안 주기 위해. 
    else:
        for nx in small[x]:
            if visited[nx] == True:
                continue

            if s+1 == k:
                answer.append(num + str(nx))
                continue

            if s+1 < k:
                dfs(nx, s+1, num + str(nx))
                visited[nx] = False  #백트래킹 

for i in range(10):
    visited = [False] * 10
    dfs(i, 0, str(i))    

print(answer[-1])           
print(answer[0]) 