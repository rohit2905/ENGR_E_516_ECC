'''

author: Rohit Goud Kalakuntla
email: rokala@iu.edu

ENGR-E 516: Assignment-1
grep mapper
'''
import sys

word = "Safari"
for line in sys.stdin:
    line = line.strip()
    if word in line:
        print('%s\t%s' % (line, 1))
