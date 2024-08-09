import sys
input = sys.stdin.readline

N = int(input().rstrip())
plan = input().split()

# 여행자의 시작 위치
row = 1
col = 1

for i in range(len(plan)):
    if plan[i] == "R" and col < N:
        col += 1
    
    elif plan[i] == "L" and col > 1:
        col -= 1
    
    elif plan[i] == "U" and row > 1:
        row -= 1
    
    elif plan[i] == "D" and row < N:
        row += 1

print(row, col)