package Controller;

import Controller.entity.*;
import Controller.helper.QueueControllerHelper;
import Service.QueueService;
import Service.SubscriberService;
import Service.entity.*;

import java.util.HashSet;
import java.util.Set;

public class QueueController {
    private final QueueService queueService;
    private final SubscriberService subscriberService;
    private final QueueControllerHelper helper = new QueueControllerHelper();

    public QueueController(QueueService queueService, SubscriberService subscriberService) {
        this.queueService = queueService;
        this.subscriberService = subscriberService;
    }

    public void createQueue(CreateQueueControllerRequest request){
        CreateQueueServiceRequest serviceRequest = helper.buildCreateQueueServiceRequest(request);
        queueService.createQueue(serviceRequest);
    }

    public void createTopic(CreateTopicControllerRequest request){
        CreateTopicServiceRequest serviceRequest = helper.buildCreateTopicServiceRequest(request);
        queueService.createTopic(serviceRequest);
    }

    public void createSubscriber(CreateSubscriberControllerRequest request){
        CreateSubscriberServiceRequest serviceRequest = helper.buildCreateSubscriberServiceRequest(request);
        queueService.createSubscriber(serviceRequest);
    }

    public void subscribeToTopic(SubscribeTopicControllerRequest request){
        SubscribeTopicServiceRequest serviceRequest = helper.buildSubscribeTopicServiceRequest(request);
        queueService.subscribeToTopic(serviceRequest);
    }

    public void publish(PublishMessageControllerRequest request){
        AddMessageToTopicServiceRequest addMessageServiceRequest = helper.buildAddMessageToTopicServiceRequest(request);
        queueService.addMessageToTopic(addMessageServiceRequest);
        Set<String> topics = new HashSet<>();
        topics.add(request.getTopicId());
        ActivateSubscriberWorkersServiceRequest serviceRequest = ActivateSubscriberWorkersServiceRequest.builder()
                .queueId(request.getQueueId()).topicIds(topics).build();
        new Thread(() -> subscriberService.activateSubscriberWorkers(serviceRequest)).start();
    }

    public void resetOffset(){

    }
}
