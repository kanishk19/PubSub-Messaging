package Service.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddMessageToTopicServiceRequest {
    private final String queueId;
    private final String message;
    private final int messageConsumptionTime;
    private final String topicId;
}
