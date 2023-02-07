# kafka-app-test
This is a maven project in which I have created a simple kafka producer and consumer.

# run zookeeper
### linux : 
bin/zookeeper-server-start.sh config/zookeeper.properties
### windows : 
bin\windows\zookeeper-server-start.bat config\zookeeper.properties

# run kafka server
### linux : 
bin/kafka-server-start.sh config/server.properties
### windows : 
bin\windows\kafka-server-start.bat config\server.properties

# create a topic
### linux : 
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test-topic-1
### windows : 
bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test-topic-1

# create a consumer
### linux : 
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic-1 --from-beginning
### windows : 
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic-1 --from-beginning

# create a producer
### linux : 
bin/kafka-console-producer.sh --bootstrap-server --broker-list localhost:9092 --topic test-topic-1
### windows : 
bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic-1