import sys
input = sys.stdin.readline

n = int(input().rstrip())

for i in range(n):
    soldier = list(map(int, input().split()))
    
    count = dict()  #각 번호의 병사 수를 딕셔너리에 저장  
    answer = -1
    for j in soldier[1::]:
        #이미 딕셔너리에 저장된 번호라면 카운트 증가 
        if j in count.keys():
            count[j] += 1
            #만약 과반수 초과라면 답이므로 break 
            if count[j] > soldier[0]//2:
                answer = j
                break
        #딕셔너리에 아직 저장 안한 번호이므로 새로 저장 
        else:
            count[j] = 1
    
    if answer == -1:
        print("SYJKGW")
    else:
        print(answer)