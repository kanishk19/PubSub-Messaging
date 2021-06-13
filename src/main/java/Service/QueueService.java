package Service;

import Service.entity.*;

public interface QueueService {
    public void createTopic(CreateTopicServiceRequest request);

    public void createSubscriber(CreateSubscriberServiceRequest request);

    public void subscribeToTopic(SubscribeTopicServiceRequest request);

    public void addMessageToTopic(AddMessageToTopicServiceRequest request);

    public void createQueue(CreateQueueServiceRequest request);
}
