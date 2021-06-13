package Service;

import Service.entity.ActivateSubscriberWorkersServiceRequest;
import commons.IdGenerator;
import commons.UUIDGenerator;
import dao.QueueDao;
import dao.entity.Subscriber;
import dao.entity.Topic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubscriberServiceImpl implements SubscriberService {
    private final QueueDao queueDao = new QueueDao();
    private final IdGenerator idGenerator = new UUIDGenerator();
    private final Map<String, SubscriberWorker> subscriberWorkerMap = new HashMap<>();

    @Override
    public void activateSubscriberWorkers(ActivateSubscriberWorkersServiceRequest request){
        //TODO: null checks
        Set<String> topicIds = request.getTopicIds();
        String queueId = request.getQueueId();
        for(String topicId: topicIds){
            Topic topic = queueDao.getTopicForId(queueId, topicId);
            List<Subscriber> subscribers = topic.getSubscribers();
            for(Subscriber subscriber: subscribers)
                startSubscriberWorker(topic, subscriber);
        }
    }

    private void startSubscriberWorker(Topic topic, Subscriber subscriber){
        String subscriberId = subscriber.getSubscriberId();
        if(!subscriberWorkerMap.containsKey(subscriberId)){
            SubscriberWorker subscriberWorker = new SubscriberWorker(topic, subscriber);
            subscriberWorkerMap.put(subscriberId, subscriberWorker);
            new Thread(subscriberWorker).start();
        }
        SubscriberWorker subscriberWorker = subscriberWorkerMap.get(subscriberId);
        subscriberWorker.wakeUpIfNeeded();
    }
}
