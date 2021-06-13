package persistence;

import dao.entity.Queue;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class QueueRepo {
    private Map<String, Queue> queueMap;

    public Queue getQueueForId(String id){
        if(queueMap==null){
            System.out.println("queue repo is empty");
            return null;
        }
        return queueMap.get(id);
    }
}
