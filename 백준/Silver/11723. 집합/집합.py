import sys
input = sys.stdin.readline

M = int(input().rstrip())
my_set = set()

for _ in range(M):
    command = input().split()
    
    if command[0] == "add":
        my_set.add(command[1])
    
    if command[0] == "remove":
        if command[1] in my_set:
            my_set.remove(command[1])

    if command[0] == "check":
        if command[1] in my_set:
            print(1)
        else:
            print(0)

    if command[0] == "toggle":
        if command[1] in my_set:
            my_set.remove(command[1])
        else:
            my_set.add(command[1])

    if command[0] == "all":
        my_set = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}
            
    if command[0] == "empty":
        my_set = set()