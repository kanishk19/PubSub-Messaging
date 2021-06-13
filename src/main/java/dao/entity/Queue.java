package dao.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Queue {
    private final String queueId;
    private final Map<String, Topic> topicMap;
    private final Map<String, Subscriber> subscriberMap;
}
