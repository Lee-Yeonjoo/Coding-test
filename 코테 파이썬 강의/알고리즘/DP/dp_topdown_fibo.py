# 피보나치 수열 - 탑다운 다이나믹 프로그래밍

# 입력값만큼 계산 결과를 기록할 테이블 배열 선언
d = [0] * 100 

def fibo(x):
    if x == 1 or x == 2:
        return 1
    
    if d[x] != 0:
        return d[x]  # 이미 계산해놓은 결과를 반환
    
    d[x] = fibo(x-1) + fibo(x-2)  # 계산 결과를 기록
    return d[x]

print(fibo(99))