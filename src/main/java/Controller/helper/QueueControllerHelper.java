package Controller.helper;

import Controller.entity.*;
import Service.entity.*;

public class QueueControllerHelper {

    public CreateQueueServiceRequest buildCreateQueueServiceRequest(CreateQueueControllerRequest request){
        return CreateQueueServiceRequest.builder().queueId(request.getQueueId()).build();
    }

    public CreateTopicServiceRequest buildCreateTopicServiceRequest(CreateTopicControllerRequest controllerRequest){
        return CreateTopicServiceRequest.builder().queueId(controllerRequest.getQueueId()).topicName(controllerRequest.getTopicName()).build();
    }

    public CreateSubscriberServiceRequest buildCreateSubscriberServiceRequest(CreateSubscriberControllerRequest request){
        return CreateSubscriberServiceRequest.builder().queueId(request.getQueueId()).subscriberName(request.getSubscriberName()).build();
    }

    public SubscribeTopicServiceRequest buildSubscribeTopicServiceRequest(SubscribeTopicControllerRequest request){
        return SubscribeTopicServiceRequest.builder().topicId(request.getTopicId()).queueId(request.getQueueId())
                .subscriberId(request.getSubscriberId()).build();
    }

    public AddMessageToTopicServiceRequest buildAddMessageToTopicServiceRequest(PublishMessageControllerRequest request){
        return AddMessageToTopicServiceRequest.builder().message(request.getMessage()).queueId(request.getQueueId())
                .messageConsumptionTime(request.getMessageConsumptionTime()).topicId(request.getTopicId()).build();
    }
}
