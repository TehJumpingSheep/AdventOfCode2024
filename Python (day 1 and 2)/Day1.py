
# Solution part 1

lines = []
with open('input.txt') as file:
    lines = [line.rstrip() for line in file]

sum = 0

list1 = []
list2 = []

for line in lines:
    list1.append(line.split(' ')[0])
    list2.append(line.split(' ')[3])

list1.sort()
list2.sort()

for idx, item in enumerate(list1):
    sum += abs(int(list1[idx]) - int(list2[idx]))

print(sum)

# solution part 2

lines = []
with open('input.txt') as file:
    lines = [line.rstrip() for line in file]

sum = 0

list1 = []
list2 = []

for line in lines:
    list1.append(line.split(' ')[0])
    list2.append(line.split(' ')[3])

for item in list1:
    sum += list2.count(item) * int(item)

print(sum)


