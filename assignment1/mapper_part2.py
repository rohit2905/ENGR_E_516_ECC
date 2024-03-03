'''

author: Rohit Goud Kalakuntla
email: rokala@iu.edu

ENGR-E 516: Assignment-1
Part-2
'''

import re
import sys

if len(sys.argv) != 2:
    print("Usage: python mapper_part2.py --hour_range=start-end")
    sys.exit(1)
hour_range = sys.argv[1].split('=')[1]
start_time, end_time = int(hour_range.split('-')[0]), int(hour_range.split('-')[1])

pattern = re.compile(r'(?P<ip>\d+\.\d+\.\d+\.\d+).*?\d{4}:(?P<hour>\d{2}):\d{2},*?')

for line in sys.stdin:
    match = pattern.search(line)
    if match and start_time <= int(match.group('hour')) <= end_time:
        print(match.group('ip'), 1)
