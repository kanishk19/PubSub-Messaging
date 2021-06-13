package Service.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateQueueServiceRequest {
    private final String queueId;
}
