#!/bin/bash

# Input file path
INPUT_FILE="/input/access.log"

# Output directory paths
OUTPUT_DIR1="/output_p1"
OUTPUT_DIR2="/output_sort"
OUTPUT_DIR3="/output_wc"
OUTPUT_DIR4="/output_grep"

# Job 1: Top 3 IPs between 00:00 - 1:00
hadoop jar "$HADOOP_STREAMING" -files mapper_part2.py,reducer_part2.py -mapper 'python3 mapper_part2.py --hour_range=0-1' -reducer 'python3 reducer_part2.py' -input "$INPUT_FILE" -output "$OUTPUT_DIR1" &

# Job 2: Sort
hadoop jar "$HADOOP_STREAMING" -files mapper_sort.py,reducer_sort.py -mapper 'python3 mapper_sort.py' -reducer 'python3 reducer_sort.py' -input "$INPUT_FILE" -output "$OUTPUT_DIR2" &

# Job 3: Word Count
hadoop jar "$HADOOP_STREAMING" -files mapper_wc.py,reducer_wc.py -mapper 'python3 mapper_wc.py' -reducer 'python3 reducer_wc.py' -input "$INPUT_FILE" -output "$OUTPUT_DIR3" &

# Job 4: Grep
hadoop jar "$HADOOP_STREAMING" -files mapper_grep.py,reducer_grep.py -mapper 'python3 mapper_grep.py' -reducer 'python3 reducer_grep.py' -input "$INPUT_FILE" -output "$OUTPUT_DIR4" &

wait
