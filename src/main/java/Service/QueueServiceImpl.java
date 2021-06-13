package Service;

import Service.entity.*;
import commons.IdGenerator;
import commons.UUIDGenerator;
import dao.QueueDao;
import dao.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class QueueServiceImpl implements QueueService{

    private final IdGenerator idGenerator = new UUIDGenerator();
    private final QueueDao queueDao = new QueueDao();

    public QueueServiceImpl() {
//        String queueId = idGenerator.generateId();
//        queueDao.initiateQueue(queueId);
    }

    @Override
    public void createTopic(CreateTopicServiceRequest request) {
        //TODO: null checks
        String queueId = request.getQueueId();
        String topicName = request.getTopicName();
        String topicId = request.getTopicName(); //idGenerator.generateId();
        List<Message> messages = new ArrayList<>();
        List<Subscriber> subscribers = new ArrayList<>();
        Topic topic = Topic.builder().topicId(topicId).topicName(topicName).messages(messages)
                .subscribers(subscribers).build();
        CreateTopicDaoRequest daoRequest = CreateTopicDaoRequest.builder().topic(topic).queueId(queueId).build();
        queueDao.createTopic(daoRequest);
    }

    @Override
    public void createSubscriber(CreateSubscriberServiceRequest request) {
        //TODO: null checks
        String subscriberName = request.getSubscriberName();
        String subscriberId = request.getSubscriberName();
        String queueId = request.getQueueId();
        Subscriber subscriber = Subscriber.builder().subscriberId(subscriberId)
                .subscriberName(subscriberName).offset(new AtomicInteger(0)).build();
        CreateSubscriberDaoRequest daoRequest = CreateSubscriberDaoRequest.builder()
                .subscriber(subscriber).queueId(queueId).build();
        queueDao.createSubscriber(daoRequest);
    }

    @Override
    public void subscribeToTopic(SubscribeTopicServiceRequest request) {
        //TODO: null checks
        String topicId = request.getTopicId();
        String queueId = request.getQueueId();
        Topic currTopic = queueDao.getTopicForId(queueId, topicId);

        String subscriberId = request.getSubscriberId();
        Subscriber currSubscriber = queueDao.getSubscriberForId(queueId, subscriberId);

        currTopic.addSubscriber(currSubscriber);
        queueDao.updateTopic(topicId, currTopic);
    }

    @Override
    public void addMessageToTopic(AddMessageToTopicServiceRequest request) {
        //TODO: null checks
        String messageText = request.getMessage();
        int messageConsumptionTime = request.getMessageConsumptionTime();
        String topicId = request.getTopicId();
        String queueId = request.getQueueId();

        Message message = Message.builder().timeToConsume(messageConsumptionTime).msg(messageText).build();
        Topic topic = queueDao.getTopicForId(queueId, topicId);
        topic.addMessage(message);
        queueDao.updateTopic(topicId, topic);
    }

    @Override
    public void createQueue(CreateQueueServiceRequest request) {
        //TODO: null checks
        String queueId = request.getQueueId();
        Queue queue = Queue.builder().topicMap(new HashMap<>()).queueId(queueId)
                .subscriberMap(new HashMap<>()).build();
        queueDao.createQueue(queue);
    }
}
