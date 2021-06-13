package dao.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private final String msg;
    private final int timeToConsume;
}
