package dao.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTopicDaoRequest {
    private final String queueId;
    private final Topic topic;
}
