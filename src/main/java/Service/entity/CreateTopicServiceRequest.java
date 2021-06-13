package Service.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTopicServiceRequest {
    private final String queueId;
    private final String topicName;
}
