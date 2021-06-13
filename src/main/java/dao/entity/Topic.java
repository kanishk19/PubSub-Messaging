package dao.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Topic {
    private final String topicId;
    private final String topicName;
    private final List<Message> messages;
    private final List<Subscriber> subscribers;

    public void addMessage(Message message){
        this.messages.add(message);
    }

    public void addSubscriber(Subscriber subscriber){
        this.subscribers.add(subscriber);
    }
}
