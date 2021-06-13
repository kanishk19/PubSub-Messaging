package dao.entity;

import lombok.Builder;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@Builder
public class Subscriber {
    private final String subscriberId;
    private final String subscriberName;
    private final AtomicInteger offset;

    public void consume(Message message) throws InterruptedException {
        System.out.println("Subscriber: " + subscriberName + " started consuming message " + message.getMsg());
        Thread.sleep(message.getTimeToConsume());
        System.out.println("Subscriber: " + subscriberName + " consumed message " + message.getMsg());
    }
}
