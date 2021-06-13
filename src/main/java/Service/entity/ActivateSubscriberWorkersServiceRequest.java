package Service.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Data
@Builder
public class ActivateSubscriberWorkersServiceRequest {
    private final String queueId;
    private final Set<String> topicIds;
}
