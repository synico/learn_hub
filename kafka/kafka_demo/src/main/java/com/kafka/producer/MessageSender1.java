package com.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class MessageSender1 {
    
    private Properties kafkaProps = new Properties();
    private Producer<String, String> producer;
    
    private void setUpProducer() {
        kafkaProps.put("bootstrap.servers", "192.168.1.219:9011,192.168.1.219:9012");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(kafkaProps);
    }
    
    public void sendMsg2Kafka() {
        if(producer == null) {
            setUpProducer();
        }
        ProducerRecord<String, String> record = new ProducerRecord<>("topic1", "Precision Products", "France");
        try {
            RecordMetadata recordMeta = producer.send(record).get();
            recordMeta.partition();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MessageSender1 sender = new MessageSender1();
        sender.sendMsg2Kafka();
    }

}
