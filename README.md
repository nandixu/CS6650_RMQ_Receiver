# CS6650_RMQ_Receiver (Assignment 3: Add Data Layer Included.)

Link to the server which contains all the sender: https://github.khoury.northeastern.edu/nandi/CS6650.git
Link to Client Repo: https://github.khoury.northeastern.edu/nandi/CS6650_Client.git

## Assignment 3

### Design Description
In Assignment 3, a PostgreSQL database is added to this project. Previously, all the data received by the receiver is added to a hashmap. Now all the data will be parsed as a skier event and be added to a PostgreDatabase. In this way, the skier information is permanently saved and could be evaluated by input sql statement. 


## Assignment 2
### Design Description
This project contains a Send and Recv folders. 

The Send folder is only for tutorial and practice purpose. It contains a SendExample that simply send a "Hello World" message to a remote Rabbit MQ. The SendThread will send the message 1000 times and the Send class will call multiple SendThreads to send messages. The Send method is implemented in the Server part. For each of the POST action, the server would generate a message and send it to the remote RabbitMQ. Please refer to https://github.khoury.northeastern.edu/nandi/CS6650 for more information.

The Recv folder contains the part2 of Assignment2. The RecvExample class will consume messages from remote RabbitMQ. The RecvThread class creates a thread that would consume the messages from a remote RabbitMQ, and the Recv class creates multiple RecvThreads to consume the messages as quickly as possible.

### Test Run Result (single servlet)
![SingleInstanceRMQ](https://media.github.khoury.northeastern.edu/user/8909/files/eececc31-e8c3-4297-8968-8467f20f7fbb)

In this case, there are 200 threads running at the same time, and each of them will complete at most 1000 doPost actions and die. Therefore, in total, there would be 200k messages send to the RabbitMQ and consumed by receivers. Moreover, there are 10 receiver threads running at the same time. With a single servlet, the queue size remains at 0 and the send/receive rates remains at around 390/s. A noticable phenomenon is that the receivers are capable of consuming messages at a much higher rate than the producers. However, the publishing rate is much lower than expected, which caused the entire system to slow down. The overall thoughput is about 490 requests per second.


### Test Run Result (load balanced servlet)
![1400perSecond](https://media.github.khoury.northeastern.edu/user/8909/files/a060e25b-3f5a-4592-97c9-1963cb567408)

In this case, the conditions are the same as the previous one, with load balanced servlet running. The result is significantly higher than single servlet. The publish rate reaches above 1400/s, and the consumption rate is still much higher than the production rate. Therefore, the queue size remains at zero.

![1400Result](https://media.github.khoury.northeastern.edu/user/8909/files/8a5625e3-e6db-4519-9e1b-c9684e009418)

The throughput also raised as the publish rate raised. The overall thoughput is around 1.467 requests/ms, which is 1467 requests/s, and matches the previous test result. 
