package Controller.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateQueueControllerRequest {
    private final String queueId;
}
