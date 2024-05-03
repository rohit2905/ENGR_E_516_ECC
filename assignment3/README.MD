### Follow the below instructions to setup docker & run the assignment

**First install docker in your system.**

Docker Desktop is a tool which provides a GUI and also runs docker daemons which can be useful to work on docker.

You can install docker desktop from the below link:

https://www.docker.com/products/docker-desktop

Once the docker desktop is installed, follow the below steps

### Steps:

1. Maintain the below project structure for easy understanding and smooth execution

project-name/ \
├── server/ \
│   ├── Dockerfile \
│   └── src/ \
├── client/ \
│   ├── Dockerfile \
│   └── src/ \
├── docker-compose.yml 


2. Write necessary code in server & client, also include the Dockerfile scripts for both server & client. The Server script should be generating a random text file of size 1KB and calculate a checksum. The Client should be receiving the file from server and store it in is file System. The client should also be validating the checksum received from the server.

3. Create an user defined network of any name

    - ` $ docker network create user-defined-network ` (replace "user-defined-network" with a name of your choice)

**(Optional step: docker-compose)**

Include a docker-compose.yml, if using docker-compose to run the docker project

4. Navigate to the project directory "cd project-name/" on the terminal and then use the following command to create docker images, volumes, network and run the containers for server and client. (Using docker-compose)

  	- ` $ docker-compose build `

    - ` $ docker-compose up -d `



5. To build and run images separately, (Server)

    - First navigate to server/ and run

    - ` $ docker build -t server Dockerfile . `

    - ` $ docker run -v volume_name:random_file_dir -p 8085:8085 --network user-defined-network --name server server `

    To build and run images separately, (Client)

    - First navigate to client/ and run

    - ` $ docker build -t client Dockerfile . `

    - ` $ docker run -v volume_name:random_file_dir -p 8086:8086 --network user-defined-network --name client client `

6. Now to verify the file generation on servr and file received on client, run the following commands to open up a bash terminal and explore the file systems of server and client

    - ` $ docker exec -it server /bin/bash `

    - ` $ docker exec -it client /bin/bash `

7. For the correct execution, the file contents should be same on server side and client side, apart from that we have checksum validation which helps us to identify if the file transfer has been success or not.

**If the file from server has been transfered to client, then we should be seeing "Checksum verified" log on client side logs. Which can be seen upon docker run of client**

8. Both the server and client should be run under a user-defined network, to verify it run the following command

    - ` $ docker network inspect rokala `

It returns a JSON, which can be used to understand that both the server and client are under a same network.
