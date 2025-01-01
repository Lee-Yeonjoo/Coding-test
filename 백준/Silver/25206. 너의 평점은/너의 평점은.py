import sys
input = sys.stdin.readline

subject = 0
sum_credit = 0
for _ in range(20):
    grade = input().split()

    if grade[2] == "A+":
        subject_rating = 4.5
    
    if grade[2] == "A0":
        subject_rating = 4.0

    if grade[2] == "B+":
        subject_rating = 3.5

    if grade[2] == "B0":
        subject_rating = 3.0

    if grade[2] == "C+":
        subject_rating = 2.5

    if grade[2] == "C0":
        subject_rating = 2.0

    if grade[2] == "D+":
        subject_rating = 1.5

    if grade[2] == "D0":
        subject_rating = 1.0

    if grade[2] == "F":
        subject_rating = 0.0
    
    if grade[2] == "P":
        continue

    subject += float(grade[1]) * subject_rating
    sum_credit += float(grade[1])

print(round(subject / sum_credit, 6))  #소수점 6번째자리까지 남도록 반올림림