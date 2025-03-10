import sys
input = sys.stdin.readline

N = int(input().rstrip())
nums = [0] * (N+1)

for i in range(1, N+1):
    nums[i] = int(input().rstrip())

visited = [False] * (N+1)
def dfs(x, start, count):  #x는 현재값, start는 초기값, count는 dfs 깊이 
    global answer  #뽑힌 정수의 개수를 세는 변수 
    visited[x] = True
    answer_num.append(x)  #뽑힌 정수를 저장하는 리스트 

    nx = nums[x]  
    if visited[nx] == False:
        #방문 전이라면 dfs하고, dfs후에 조건을 만족하지 않는 경우에만 백트래킹 
        if not dfs(nx, start, count + 1):
            visited[x] = False  
            answer_num.pop()
            return False
    else:
        #방문 후인데 초기값과 같다면 조건을 만족하므로 answer에 dfs깊이만큼 증가 
        if nx == start:
            answer += count
        else:  #초기값과 다르면 조건 불만족. 값을 원래대로 복구
            visited[x] = False
            answer_num.pop()
            return False
    return True

answer = 0
answer_num = []
for i in range(1, N+1):
    if visited[i] == False:
        dfs(i, i, 1)
        visited[i] = True 

print(answer)
answer_num.sort()
for i in answer_num:
    print(i)