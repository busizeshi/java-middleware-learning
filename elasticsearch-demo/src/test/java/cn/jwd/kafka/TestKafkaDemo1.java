package cn.jwd.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestKafkaDemo1 {

    @Autowired
    private SendAndReceiver sendAndReceiver;

    @Test
    public void testSend() {
        sendAndReceiver.send("hello kafka");
    }

    @Test
    public void testReceive() {
        String message = sendAndReceiver.receive("hello kafka");
        System.out.println(message);
    }
}
