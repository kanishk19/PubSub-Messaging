package Service.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSubscriberServiceRequest {
    private final String queueId;
    private final String subscriberName;
}
