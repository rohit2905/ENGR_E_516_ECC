'''

author: Rohit Goud Kalakuntla
email: rokala@iu.edu

ENGR-E 516: Assignment-1
Part-1
'''


from operator import itemgetter
import sys

dict_ip_count = {}

for line in sys.stdin:
    line = line.strip()
    ip, num = line.split('\t')
    try:
        num = int(num)
        dict_ip_count[ip] = dict_ip_count.get(ip, 0) + num
    except ValueError:
        pass

sorted_dict_ip_count = sorted(dict_ip_count.items(), key=itemgetter(1), reverse=True)

index = 0
for ip, count in sorted_dict_ip_count:
    hour, ip = ip.split("]")
    hip = hour + ']  ' + ip
    print('%s\t%s'%(hip, count))
    index+=1
    if index == 3:
        break