import sys
input = sys.stdin.readline

x = input().rstrip()  # rstrip()을 통해 입력 받을 때 마지막 엔터는 제외하고 입력받아야 한다!
alphabet = []
sum = 0

for i in range(len(x)):
    if ord(x[i]) >= 65 and ord(x[i]) <= 92: # 알파벳인 경우. str[i].isalpha()메소드를 사용해도됨
        alphabet.append(x[i])
    else:
        sum += int(x[i])

alphabet = sorted(alphabet)  # 정렬 결과를 리스트에 다시 담는 거 잊지말자
for i in alphabet:
    print(i, end="")
print(sum)

# 답안 코드의 출력 - 반복문을 안 쓸 수 있다.
if sum != 0:
    alphabet.append(str(sum))
print(''.join(alphabet))