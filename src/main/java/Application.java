import Controller.QueueController;
import Controller.entity.*;
import Service.QueueService;
import Service.QueueServiceImpl;
import Service.SubscriberService;
import Service.SubscriberServiceImpl;


public class Application {
    private final static QueueService service = new QueueServiceImpl();
    private final static SubscriberService SUBSCRIBER_SERVICE = new SubscriberServiceImpl();
    private final static QueueController controller = new QueueController(service, SUBSCRIBER_SERVICE);

    public static void main(String[] args){
        createQueues();
        System.out.println();

        createTopics();
        System.out.println();

        createSubscribers();
        System.out.println();

        subscribeToTopics();
        System.out.println();

        publishMessagesToTopic();
    }

    private static void createQueues(){
        CreateQueueControllerRequest createQueueRequest = CreateQueueControllerRequest.builder().queueId("q1").build();
        controller.createQueue(createQueueRequest);
    }

    private static void publishMessagesToTopic(){
        PublishMessageControllerRequest publishMessageRequest1 = PublishMessageControllerRequest.builder()
                .queueId("q1").message("m1").messageConsumptionTime(10000).topicId("t1").build();
        PublishMessageControllerRequest publishMessageRequest2 = PublishMessageControllerRequest.builder()
                .queueId("q1").message("m2").messageConsumptionTime(10000).topicId("t1").build();
        PublishMessageControllerRequest publishMessageRequest3 = PublishMessageControllerRequest.builder()
                .queueId("q1").message("m3").messageConsumptionTime(5000).topicId("t2").build();
        controller.publish(publishMessageRequest1);
        controller.publish(publishMessageRequest2);
        controller.publish(publishMessageRequest3);
    }

    private static void subscribeToTopics(){
        SubscribeTopicControllerRequest subscribeTopicRequest1 = SubscribeTopicControllerRequest.builder()
                .queueId("q1").topicId("t1").subscriberId("s1").build();
        SubscribeTopicControllerRequest subscribeTopicRequest2 = SubscribeTopicControllerRequest.builder()
                .queueId("q1").topicId("t1").subscriberId("s2").build();
        SubscribeTopicControllerRequest subscribeTopicRequest3 = SubscribeTopicControllerRequest.builder()
                .queueId("q1").topicId("t2").subscriberId("s3").build();
        controller.subscribeToTopic(subscribeTopicRequest1);
        controller.subscribeToTopic(subscribeTopicRequest2);
        controller.subscribeToTopic(subscribeTopicRequest3);
    }

    private static void createSubscribers(){
        CreateSubscriberControllerRequest createSubscriberRequest1 = CreateSubscriberControllerRequest.builder()
                .queueId("q1").subscriberName("s1").build();
        CreateSubscriberControllerRequest createSubscriberRequest2 = CreateSubscriberControllerRequest.builder()
                .queueId("q1").subscriberName("s2").build();
        CreateSubscriberControllerRequest createSubscriberRequest3 = CreateSubscriberControllerRequest.builder()
                .queueId("q1").subscriberName("s3").build();
        controller.createSubscriber(createSubscriberRequest1);
        controller.createSubscriber(createSubscriberRequest2);
        controller.createSubscriber(createSubscriberRequest3);
    }

    private static void createTopics(){
        CreateTopicControllerRequest createTopicRequest1 = CreateTopicControllerRequest
                .builder().queueId("q1").topicName("t1").build();
        CreateTopicControllerRequest createTopicRequest2 = CreateTopicControllerRequest
                .builder().queueId("q1").topicName("t2").build();
        CreateTopicControllerRequest createTopicRequest3 = CreateTopicControllerRequest
                .builder().queueId("q1").topicName("t3").build();
        controller.createTopic(createTopicRequest1);
        controller.createTopic(createTopicRequest2);
        controller.createTopic(createTopicRequest3);
    }

}
