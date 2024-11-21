import sys
input = sys.stdin.readline

word = input().rstrip()

cnt = 0

while "c=" in word:
    cnt += 1
    word = word.replace("c=", ".", 1)
    
while "c-" in word:
    cnt += 1
    word = word.replace("c-", ".", 1)
    
while "dz=" in word:
    cnt += 1
    word = word.replace("dz=", ".", 1)
    
while "d-" in word:
    cnt += 1
    word = word.replace("d-", ".", 1)

while "lj" in word:
    cnt += 1
    word = word.replace("lj", ".", 1)
    
while "nj" in word:
    cnt += 1
    word = word.replace("nj", ".", 1)
    
while "s=" in word:
    cnt += 1
    word = word.replace("s=", ".", 1)
    
while "z=" in word:
    cnt += 1
    word = word.replace("z=", ".", 1)

#남은 문자 수 세기
for i in word:
    if i != '.':
        cnt += 1

print(cnt)