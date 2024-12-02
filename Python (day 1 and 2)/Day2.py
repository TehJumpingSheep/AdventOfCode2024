
# Solution part 1

lines = []
with open('input.txt') as file:
    lines = [line.rstrip() for line in file]

sum = 0

for line in lines:
    split = line.split(' ')
    actualarray = []
    for item in split:
        if(item != ' '):
            actualarray.append(int(item))

    if(all(actualarray[i] < actualarray[i+1] for i in range(len(actualarray) - 1)) or all(actualarray[i] > actualarray[i+1] for i in range(len(actualarray) - 1))):
        print(actualarray)
        print([abs(actualarray[i+1]-actualarray[i]) for i in range(len(actualarray)-1)])
        if all(i < 4 for i in [abs(actualarray[i+1]-actualarray[i]) for i in range(len(actualarray)-1)]):
            sum += 1

print(sum)

# solution part 2

lines = []
with open('input.txt') as file:
    lines = [line.rstrip() for line in file]

sum = 0

for line in lines:
    split = line.split(' ')
    actualarray = []
    for item in split:
        if(item != ' '):
            actualarray.append(int(item))

    if(all(actualarray[i] < actualarray[i+1] for i in range(len(actualarray) - 1)) or all(actualarray[i] > actualarray[i+1] for i in range(len(actualarray) - 1))):
        if all(i < 4 for i in [abs(actualarray[i+1]-actualarray[i]) for i in range(len(actualarray)-1)]):
            sum += 1
            continue

    for i in range(len(actualarray)):

        temp = actualarray.copy()
        del actualarray[i]

        if(all(actualarray[i] < actualarray[i+1] for i in range(len(actualarray) - 1)) or all(actualarray[i] > actualarray[i+1] for i in range(len(actualarray) - 1))):
            if all(i < 4 for i in [abs(actualarray[i+1]-actualarray[i]) for i in range(len(actualarray)-1)]):
                sum += 1
                actualarray = temp
                break

        actualarray = temp
print(sum)


