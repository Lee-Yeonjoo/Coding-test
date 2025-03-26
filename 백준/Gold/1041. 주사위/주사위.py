import sys
input = sys.stdin.readline

N = int(input().rstrip())
dice = list(map(int, input().split()))

#인접한 면이 2개일 때의 합의 최소값 찾기 - 완전 탐색 
min_two_faces = 100
for i in range(6):
    for j in range(6):
        #인접하지 않거나 같은 면이면 패스스
        if j == (5-i) or i == j:
            continue
        
        #최소값 갱신 - 그리디 
        if (dice[i]+ dice[j]) < min_two_faces:
            min_two_faces = dice[i] + dice[j]

#인접한 면이 3개일 때의 합의 최소값 찾기
min_three_faces = 150
for i in range(6):
    #두번째 인접한 면 찾기
    for j in range(6):
        #같은 면이거나 인접하지 않은 면이면 패스 
        if i == j or j == (5-i):
            continue

        #세번째 인접한 면 찾기 
        for k in range(6):
            if i == k or j == k or k == (5-i) or k == (5-j):
                continue
            
            #최소값 갱신 
            sum_dice = dice[i] + dice[j] + dice[k]
            if sum_dice < min_three_faces:
                min_three_faces = sum_dice

#주사위가 1개뿐일 땐 else 식으로 하면 틀림
if N == 1:
    print(sum(dice) - max(dice))
else:
    #면이 1개인 경우, 인접한 면이 2개인 경우, 인접한 면이 3개인 경우 각 각의 개수를 N값에 따라 식을 세움 
    print(((N-2)*(N-1) * 4 + (N-2)**2)*min(dice) + ((N-1)*4 + (N-2)*4)*(min_two_faces) + (4*min_three_faces)) 