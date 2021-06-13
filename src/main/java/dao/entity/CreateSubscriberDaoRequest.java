package dao.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSubscriberDaoRequest {
    private final String queueId;
    private final Subscriber subscriber;
}
