'''

author: Rohit Goud Kalakuntla
email: rokala@iu.edu

ENGR-E 516: Assignment-1
Part-1 Round 2 Reducer
'''

import sys
from collections import defaultdict

ip_dict = defaultdict(lambda: defaultdict(int))

for line in sys.stdin:
    line = line.strip()
    hr, ip, cnt = line.split()

    cnt = int(cnt) 
    ip_dict[hr][ip] += cnt
    
hr="HOUR"
ip="IP"
cnt="COUNT"
border = "==============================="
print(border)
print(f"{hr}\t{ip}\t\t{cnt}")
print(border)
for hr, ip_count in ip_dict.items():
    sorted_ips = sorted(ip_count.items(), key=lambda x: x[1], reverse=True)[:3]
    for ip, cnt in sorted_ips:
        print(f"{hr}\t{ip}\t{cnt}")
    print(border)

