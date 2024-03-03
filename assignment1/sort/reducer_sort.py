'''

author: Rohit Goud Kalakuntla
email: rokala@iu.edu

ENGR-E 516: Assignment-1
sort reducer
'''

import sys

lines = sys.stdin.readlines()
lines.sort()
for line in lines:
    line = line.strip()
    print(line.split("\t")[0])
