# 유클리드 호제법으로 최대공약수 구하기
def gcd(a, b):
    if a % b == 0:  # a가 b의 배수라면 b가 최대공약수
        return b
    else:
        return gcd(b, a % b)

print(gcd(192, 162))  # a와 b 순서에 상관없이 넣어도 된다.