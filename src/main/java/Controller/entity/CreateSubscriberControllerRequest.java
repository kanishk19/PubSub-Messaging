package Controller.entity;

import com.sun.istack.internal.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSubscriberControllerRequest {
    @NotNull
    private final String queueId;
    @NotNull
    private final String subscriberName;
}
