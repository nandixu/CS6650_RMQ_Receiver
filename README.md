# CS6650_RMQ_Receiver

Link to the server which contains all the sender: https://github.khoury.northeastern.edu/nandi/CS6650.git
Link to Client Repo: https://github.khoury.northeastern.edu/nandi/CS6650_Client.git

### Design Description
This project contains a Send and Recv folders. 

The Send folder is only for tutorial and practice purpose. It contains a SendExample that simply send a "Hello World" message to a remote Rabbit MQ. The SendThread will send the message 1000 times and the Send class will call multiple SendThreads to send messages. The Send method is implemented in the Server part. For each of the POST action, the server would generate a message and send it to the remote RabbitMQ. Please refer to https://github.khoury.northeastern.edu/nandi/CS6650 for more information.

The Recv folder contains the part2 of Assignment2. The RecvExample class will consume messages from remote RabbitMQ. The RecvThread class creates a thread that would consume the messages from a remote RabbitMQ, and the Recv class creates multiple RecvThreads to consume the messages as quickly as possible.

### Test Run Result (single servlet)


### Test Run Result (load balanced servlet)
