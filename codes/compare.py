'''
@anirudha
12:26:55 17-11-2020
'''
choose = input("Original [press 0] or New [press 1]?")
if(choose == "1"):
  filename1 = input("input file: ") 
  filename2 = input("Output file: ") 
else:
  filename1 = "ans.txt"
  filename2 = "ansmy.txt"

file1 = open(filename1).readlines() 
 
file1_line = [] 
 
for lines in file1: 
 file1_line.append(lines) 
 
file2 = open(filename2).readlines() 
 
file2_line = [] 
 
for lines in file2: 
 file2_line.append(lines) 
 
n = 0 
unmatch = 0;
match = 0;
first = 0;
if len(file1) > len(file2): 
 for line in file2_line: 
  if line != file1_line[n]: 
   print("Not Match:","Line :",n + 1,filename1,":",line,"|",filename2,":",file2_line[n]) 
   if first == 0:
    first = n;
   n += 1 
   unmatch +=1
  else: 
   n += 1 
   match += 1
 print("Length Of File is ",filename1,"is greater than",filename2,len(file1),">",len(file2)) 
 
 
elif len(file1) < len(file2): 
 n = 0 
 for line in file1_line: 
  if line != file2_line[n]: 
   print("Not Match:","Line :",n + 1,filename2,":",line,"|",filename1,":",file1_line[n]) 
   if first == 0:
    first = n;
   n += 1 
   unmatch +=1
  else: 
   n += 1 
   match += 1
 print("Length Of File is ",filename1,"is less than",filename2,len(file1),"<",len(file2)) 
 
 
else: 
  n = 0 
  for line in file1_line: 
    if line != file2_line[n]: 
      print("Not Match:","Line :",n + 1,filename1,":",line,"|",filename2,":",file2_line[n]) 
      if first == 0:
        first = n;
      n += 1 
      unmatch +=1
    else: 
      n += 1 
      match += 1
  print("Length Of File is ",filename1,"Equals",filename2,len(file1),"==",len(file2)) 


print ("unmatch = " , unmatch , " match = " , match, "firstError = ", first )