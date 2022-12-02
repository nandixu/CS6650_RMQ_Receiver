# CS6650_RMQ_Receiver (Assignment 3: Add Data Layer Included.)

Link to the server which contains all the sender: https://github.khoury.northeastern.edu/nandi/CS6650.git
Link to Client Repo: https://github.khoury.northeastern.edu/nandi/CS6650_Client.git

## Assignment 3

### Design Description
In Assignment 3, a PostgreSQL database is added to this project. Previously, all the data received by the receiver is added to a hashmap. Now all the data will be parsed as a skier event and be added to a PostgreSQL Database. In this way, the skier information is permanently saved and could be evaluated by input sql statement. 

The Database part is composed of 3 parts. The PostSQL database currently is hosted at local computer. The SkierDAO is the database access object which serves to provide access to the postgres database. Currently there is only one service called addNewSkierByMessage, which takes a string as an input, transform the string into proper skier data, and add the new skier to the database. The ConnectionManager component provides connection to the database at once, so the DAO would only create one connection and load multiple data, which would improve efficiency.

Therefore, the overall process is built up. Client sent 200k message to the server, the server would generate corresponding messages and send them to the RMQ. The RMQ sends messages to the receivers as soon as possible, and there are 50 receiver threads waiting to receive the message. For any message that each thread received, it transform the message into proper data and put it into the database.

Client threads amount = 200 threads;

Reiceiver threads amount = 50 threads;

Total message amount = 200k skier event message, generated randomly;

Some techniques I used to improve efficiency:

1. Create one connection with each receiver thread and use the single connection to load all data. 
2. 
3. Eliminate unnecessary console output.


### Assignment 3 result

Below is the RMQ processing result.

![A3Result1](https://media.github.khoury.northeastern.edu/user/8909/files/c7020820-a4ec-456d-9e93-bcbf53f133f0)

The queue stays at 0, which is a good sign. It means all the messages delivered into RMQ is also delivered out instantly. I have tested this part with 1 single receiver thread, which results in an increasing queue size. The large amount of receiver thread must contribute a lot to efficiency. 

![A3Result2](https://media.github.khoury.northeastern.edu/user/8909/files/ea45c2a2-86c7-46a9-b556-19566793610b)

It is expected that loading data into database would significantly increase the amount of time used to handle each request. In Assignment 1, the Throughput is 28 requests per second. In Assignment 2, the Throughput is roughly 1.47 request per second. In Assignment 3, it drops to 1.35 request per second. It is obvious that most of the time is spent with RMQ, where message is delivered in and deliverd out. Saving data into database would have minor influence on efficiency compared with RMQ process.

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
