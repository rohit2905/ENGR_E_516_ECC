'''

author: Rohit Goud Kalakuntla
email: rokala@iu.edu

ENGR-E 516: Assignment-1
Part-1 Round 2 Mapper
'''
import re
import sys
pat = re.compile('(?P<ip>\d+.\d+.\d+.\d+).*?\d{4}:(?P<hour>\d{2}):\d{2},*?')
for line in sys.stdin:
        match = pat.search(line)
        if match:
                print('%s\t%s' % ('['+match.group('hour') + ':00' + '] '+match.group('ip'), 1))