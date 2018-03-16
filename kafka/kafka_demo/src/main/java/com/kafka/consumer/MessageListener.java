package com.kafka.consumer;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageListener {
    
    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);
    
    private Properties kafkaProps = new Properties();
    private KafkaConsumer<String, String> consumer;
    
    private void setUpProducer() {
        kafkaProps.put("bootstrap.servers", "192.168.1.219:9011,192.168.1.219:9012");
        kafkaProps.put("group.id", "ConsumerGroup1");
        kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put("client.id", "consumer");
        consumer = new KafkaConsumer<String, String>(kafkaProps);
    }
    
    public void listenOnBroker() {
        if(consumer == null) {
            setUpProducer();
        }
//        consumer.subscribe(Collections.singletonList("topic1"));
        //build topic partition for consumer
        TopicPartition topicPartition = new TopicPartition("topic1", 3);
        consumer.assign(Collections.singleton(topicPartition));
        //poll messages by offset
        consumer.seek(topicPartition, 0L);
        //loop
        try {
            while(true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for(ConsumerRecord<String, String> record : records) {
//                    TimeUnit.SECONDS.sleep(10);
                    log.info("\nMsg Listener1 ===> [ topic = {}, partition = {}, offset = {}, key = %s, message = {} ]\n", 
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
//                    System.out.println(record.topic() + ", " + record.partition() + ", " + record.offset() + ", " + record.key() + ", " + record.value());
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        } finally {
            consumer.close();
        }
    }

    public static void main(String[] args) {
        MessageListener msgConsumer = new MessageListener();
        msgConsumer.listenOnBroker();
    }

}
