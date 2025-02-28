import sys
input = sys.stdin.readline

name = input().rstrip()

#입력받은 문자열의 각 알파벳의 개수 세기
count = [0] * 26
for i in name:
    count[ord(i) - 65] += 1

#홀수 개인 알파벳이 몇 개인지 세면서, 해당 알파벳 기록 
check = 0
odd_num = ""
for i in range(26):
    if count[i] % 2 == 1:
        check += 1
        odd_num = chr(i + 65)

answer = ""
#만약 홀수 개인 알파벳의 수가 2개 이상이면 팰린드롬이 될 수 없다. 
if check >= 2:
    print("I'm Sorry Hansoo")
else:
    #count배열을 순회하면서 개수의 절반을 문자열로 조립 - 이미 사전 순으로 정렬되어 있다. 
    for i in range(26):
        if count[i] == 0:
            continue
        
        for _ in range(count[i] // 2):
            answer += chr(i + 65)
    
    #answer문자열을 뒤집기 
    answer_reverse = ""
    for r in answer:
        answer_reverse = r + answer_reverse
    
    #최종 조합 
    print(answer + odd_num + answer_reverse)


