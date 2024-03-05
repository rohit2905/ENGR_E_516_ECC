
'''

author: Rohit Goud Kalakuntla
email: rokala@iu.edu

ENGR-E 516: Assignment-1
Part-2
'''

from operator import itemgetter
import sys

dict_ip_count = {}

s=sys.stdin.readline().strip()
st, en = s.split(' ')
for line in sys.stdin:
    line = line.strip()
    ip, num = line.split(' ')
    try:
        num = int(num)
        dict_ip_count[ip] = dict_ip_count.get(ip, 0) + num
    except ValueError:
        pass

sorted_dict_ip_count = sorted(dict_ip_count.items(), key=itemgetter(1), reverse=True)

index = 0
print("==============================================")
print("  Top 3 IP Addresses for hour range: ", str(st+" - "+en))
print("==============================================")
for ip, count in sorted_dict_ip_count:
    print('%s\t%s'%(ip, count))
    index+=1
    if index == 3:
        break