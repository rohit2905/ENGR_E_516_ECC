'''

author: Rohit Goud Kalakuntla
email: rokala@iu.edu

ENGR-E 516: Assignment-1
grep reducer
'''
from operator import itemgetter
import sys

pre_word,pre_count,word = None,0,None

for line in sys.stdin:
    line = line.strip()
    word, count = line.split('\t', 1)
    print ('%s' % (word))
