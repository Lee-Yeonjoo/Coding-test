import sys
input = sys.stdin.readline

T = int(input().rstrip())

for _ in range(T):

    n, m = map(int, input().split())

    data = list(map(int, input().split()))

    gold_mine = []
    for i in range(n):
        gold_mine.append(data[i*m:(i+1)*m])
    
    d= [0] * (m)   # 현재의 금의 크기
    sum = [0] * (m)
    for i in range(0, n):
        d[0] = max(d[0], gold_mine[i][0])
        
    sum[0] += d[0]  # 금의 전체 합
    idx = []        # 현재의 금의 크기가 큰 인덱스
    for i in range(1, m):
        for j in range(0, n):
            pass
    d[i] = d[i-1] + max()