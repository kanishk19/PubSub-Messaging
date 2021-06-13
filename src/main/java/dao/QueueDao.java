package dao;

import dao.entity.*;
import persistence.QueueRepo;

import java.util.HashMap;
import java.util.Map;

public class QueueDao {
    private static QueueRepo queueRepo;

    public QueueDao() {

    }

//    public void initiateQueue(String queueId){
//        if(queueRepo!=null){
//            System.out.println("an initiated queue already exists");
//            return;
//        }
//        Map<String, Topic> topicMap = new HashMap<>();
//        Map<String, Subscriber> subscriberMap = new HashMap<>();
//        queue = Queue.builder().queueId(queueId).topicMap(topicMap).subscriberMap(subscriberMap).build();
//        System.out.println("Created new queue with id: " + queueId);
//    }

    public void createQueue(Queue queue){
        Map<String, Queue> queueMap = new HashMap<>();
        if(queueRepo == null){
            queueRepo = QueueRepo.builder().queueMap(queueMap).build();
        }
        queueMap = queueRepo.getQueueMap();
        queueMap.put(queue.getQueueId(), queue);
        System.out.println("Added new queue: " + queue);
    }

    public void createTopic(CreateTopicDaoRequest request){
        if (request == null){
            System.out.println("invalid request, " + request);
            throw new IllegalArgumentException("invalid request");
        }

        String queueId = request.getQueueId();
        Topic topic = request.getTopic();

        Queue queue = queueRepo.getQueueForId(queueId);
        Map<String, Topic> topicMap = queue.getTopicMap();
        topicMap.put(topic.getTopicId(), topic);
        System.out.println("New topic created " + topic);
    }

    public Topic getTopicForId(String queueId, String topicId){
        Queue queue = queueRepo.getQueueForId(queueId);
        Map<String, Topic> topicMap = queue.getTopicMap();
        return topicMap.get(topicId);
    }

    public void updateTopic(String topicId, Topic topic){
//        queue.getTopicMap().put(topicId, topic);
        System.out.println("Updating topic with id: " + topicId + " to " + topic);
    }

    public void createSubscriber(CreateSubscriberDaoRequest request){
        // TODO: null checks
        String queueId = request.getQueueId();
        Subscriber subscriber = request.getSubscriber();
        Queue queue = queueRepo.getQueueForId(queueId);
        Map<String, Subscriber> subscriberMap = queue.getSubscriberMap();
        subscriberMap.put(subscriber.getSubscriberId(), subscriber);
        System.out.println("New subscriber created " + subscriber);
    }

    public Subscriber getSubscriberForId(String queueId, String subscriberId){
        Queue queue = queueRepo.getQueueForId(queueId);
        Map<String, Subscriber> subscriberMap = queue.getSubscriberMap();
        return subscriberMap.get(subscriberId);
    }

}
