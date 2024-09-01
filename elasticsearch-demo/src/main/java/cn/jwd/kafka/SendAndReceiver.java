package cn.jwd.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class SendAndReceiver {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC = "test";

    public void send(String message) {
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(TOPIC, message);
        send.addCallback(result -> System.out.println("发送成功"), ex -> System.out.println("发送失败"));
    }

    @KafkaListener(topics = TOPIC)
    public String receive(String message) {
        return message;
    }
}
