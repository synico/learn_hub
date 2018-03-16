package com.kafka.producer;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageSender1 {
    
    private static final Logger log = LoggerFactory.getLogger(MessageSender1.class);
    
    private Properties kafkaProps = new Properties();
    private Producer<String, String> producer;
    
    private void setUpProducer() {
        kafkaProps.put("bootstrap.servers", "192.168.1.219:9011,192.168.1.219:9012");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("client.id", "producer1");
        producer = new KafkaProducer<String, String>(kafkaProps);
    }
    
    public void sendMsg2Kafka() {
        if(producer == null) {
            setUpProducer();
        }
        List<PartitionInfo> partitions = producer.partitionsFor("topic1");
        partitions.forEach(System.out::println);
        
        ProducerRecord<String, String> record = null;
        try {
            for(PartitionInfo partition : partitions) {
                int partitionId = partition.partition();
                record = new ProducerRecord<>("topic1", partitionId, "Message sender" + partitionId, "content" + System.currentTimeMillis());
                RecordMetadata recordMeta = producer.send(record).get();
                log.info("offset: {}", recordMeta.offset());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MessageSender1 sender = new MessageSender1();
        sender.sendMsg2Kafka();
    }

}
