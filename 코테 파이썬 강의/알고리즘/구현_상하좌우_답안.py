n = int(input())
x, y = 1, 1
plans = input().split()

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
move_types = ['L', 'R', 'U', 'D']

for plan in plans:
	# 여기서 nx, ny를 선언하지 않아도 된다.
	for i in range(len(move_types)):
		if plan == move_types[i]:
			nx = x + dx[i]
			ny = y + dy[i]
			
	if nx < 1 or ny < 1 or nx > n or ny > n: # 여기서 바로 참조 가능
		continue
	x, y = nx, ny

print(x, y)