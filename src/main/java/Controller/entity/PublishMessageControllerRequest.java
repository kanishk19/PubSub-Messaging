package Controller.entity;

import com.sun.istack.internal.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PublishMessageControllerRequest {
    @NotNull
    private final String queueId;
    @NotNull
    private final String message;
    private final int messageConsumptionTime;
    @NotNull
    private final String topicId;
}
