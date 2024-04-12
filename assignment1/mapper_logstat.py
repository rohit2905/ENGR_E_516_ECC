'''

author: Rohit Goud Kalakuntla
email: rokala@iu.edu

ENGR-E 516: Assignment-1
Example - 1
'''



import re
import sys
pat = re.compile('(?P<ip>\d+.\d+.\d+.\d+).*?"\w+(?P<subdir>.*?)')
for line in sys.stdin:
        match = pat.search(line)
        if match:
                print ('%s\t%s'%(match.group('ip'), 1))