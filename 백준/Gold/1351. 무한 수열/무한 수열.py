import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N, P, Q = map(int, input().split())
dp = dict()  #리스트를 쓰면 메모리초과 발생 
dp[0] = 1

#탑다운(재귀) 방식으로 구해야 효율적인 경우 
def topdown(x):
    if x not in dp:
        dp[x] = topdown(x//P) + topdown(x//Q)
    return dp[x]

print(topdown(N))