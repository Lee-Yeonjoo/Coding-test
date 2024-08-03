import sys
input = sys.stdin.readline

N = int(input().strip())

fearList = map(int, input().split())
fearList = sorted(fearList)   # 공포도를 오름차순으로 정렬

cnt = 0   # 결성된 그룹의 수
guild = []   # 그룹을 만들 때 임시로 몇 명인지 표시할 수단 -> 리스트 아니어도 됨
for i in fearList:
    guild.append(i)

    if len(guild) >= i:
        cnt += 1
        guild = []

print(cnt)        