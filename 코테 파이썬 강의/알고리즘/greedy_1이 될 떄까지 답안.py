n, k = map(int, input().split())

result = 0  # 연산 횟수를 기록

while True:
	# N이 K로 나누어 떨어지는 수가 될 때까지 1씩 빼는걸 한번에 빼기
	target = (n//k) * k   # target은 K로 나누어 떨어지는, N에서 가장 가까운 수
	result += (n - target)  # n과 target의 차이 = 1 빼는 연산의 실행 횟수 -> result에 기록
	n = target  # 1 빼는 연산의 횟수를 result에 기록했으므로 n을 target으로 갱신
	
	# n이 k보다 작아서 더 이상 나눌 수 없을 때 탈출
	if n < k:
		break
	
	# k로 나누기
	result += 1
	n //= k

# 마지막으로 남은 수에 1씩 빼기
result += (n-1)  # n이 1이 되야하는거니까 n과 1의 차이만큼이 1을 빼는 연산횟수니까 result에 더한다.
print(result)