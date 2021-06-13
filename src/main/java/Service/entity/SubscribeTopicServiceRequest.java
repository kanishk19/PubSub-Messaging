package Service.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscribeTopicServiceRequest {
    private final String queueId;
    private final String topicId;
    private final String subscriberId;
}
