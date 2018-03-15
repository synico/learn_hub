package com.kafka.consumer;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
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
        consumer = new KafkaConsumer<String, String>(kafkaProps);
    }
    
    public void listenOnBroker() {
        if(consumer == null) {
            setUpProducer();
        }
        consumer.subscribe(Collections.singletonList("topic1"));
        //loop
        try {
            while(true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for(ConsumerRecord<String, String> record : records) {
                    log.info("topic = %s, partition = %s, offset = %s, key = %s, message = %s\n", 
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                    System.out.println(record.topic() + ", " + record.partition() + ", " + record.offset() + ", " + record.key() + ", " + record.value());
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
