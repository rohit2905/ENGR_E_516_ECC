for node in rokala-worker1 rokala-worker2; do
    scp /home/exouser/hadoop/etc/hadoop/* $node:/home/exouser/hadoop/etc/hadoop/;
done
