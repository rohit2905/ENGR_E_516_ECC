cd ~ 
wget https://archive.apache.org/dist/spark/spark-3.5.0/spark-3.5.0-bin-hadoop3.tgz
tar -xvf spark-3.5.0-bin-hadoop3.tgz  
rm ./spark-3.5.0-bin-hadoop3.tgz
scp -r ./spark-3.5.0-bin-hadoop3 exouser@rokala-worker1:~/ # Copy files to worker1
scp -r ./spark-3.5.0-bin-hadoop3 exouser@rokala-worker2:~/ # Copy files to worker2

# For manager Machine
sudo mv spark-3.5.0-bin-hadoop3 /opt/ 
sudo ln -sf /opt/spark-3.5.0-bin-hadoop3 /opt/spark #
sudo chown exouser /opt/spark* -R
sudo chmod g+rwx /opt/spark* -R 

# For worker1 Machine
ssh rokala-worker1
sudo mv spark-3.5.0-bin-hadoop3 /opt/
sudo ln -sf /opt/spark-3.5.0-bin-hadoop3 /opt/spark
sudo chown exouser /opt/spark* -R 
sudo chmod g+rwx /opt/spark* -R 
exit 

# For worker2 Machine
ssh rokala-worker22
sudo mv spark-3.5.0-bin-hadoop3 /opt/ 
sudo ln -sf /opt/spark-3.5.0-bin-hadoop3 /opt/spark
sudo chown exouser /opt/spark* -R 
sudo chmod g+rwx /opt/spark* -R
exit 