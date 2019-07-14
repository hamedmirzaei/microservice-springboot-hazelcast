package microservice.springboot.hazelcast;

import com.hazelcast.core.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HazelcastInstance1Application.class)
public class HazelcastInstance1ApplicationTests {

    @Autowired
    HazelcastInstance hazelcastInstance;

    @Test
    public void hazelcastQueuePutPollWorks() {
        IQueue queue = hazelcastInstance.getQueue("q1");
        try {
            queue.put("Hello");
            queue.put("World!");
            assertTrue(queue.poll().equals("Hello"));
            assertTrue(queue.poll().equals("World!"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hazelcastTopicPublishSubscribeWorks() {
        ITopic topic = hazelcastInstance.getTopic("t1");
        topic.addMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                assertTrue(message.getMessageObject().toString().equals("Hello"));
            }
        });
        topic.publish("Hello");
    }

}
