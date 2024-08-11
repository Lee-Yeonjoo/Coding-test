# 반복문으로 구현한 팩토리얼
def factorial_iterative(n):
    result = 1
    for i in range(1, n+1):
        result *= i
    return result

# 재귀함수로 구현한 팩토리얼
def factorial_recursive(n):
    if n <= 1:
        return 1
    return n * factorial_recursive(n-1)  # 수학의 점화식

print('반복문:', factorial_iterative(5))
print('재귀함수:', factorial_recursive(5))
