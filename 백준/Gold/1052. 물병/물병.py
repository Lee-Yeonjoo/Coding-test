import sys
input = sys.stdin.readline

N, K = map(int, input().split())

num_binary = 10001  #이진수일 때 1의 개수 = 최종적으로 만들 수 있는 합친 물병의 개수 
cnt = 0  #N을 증가시킬때마다 카운트
#K보다 작거나 같으면 조건 만족 
while num_binary > K:  
    b = list(bin(N)[2:])  #이진수 변환 후 앞에 0b 자르기 
    num_binary = b.count('1')  #1의 개수 세기 
    cnt += 1
    N += 1

print(cnt-1)