package topicmanager;

import apiREST.apiREST_TopicManager;
import util.Subscription_check;
import util.Topic;
import util.Topic_check;
import java.util.List;
import publisher.Publisher;
import publisher.PublisherStub;
import subscriber.Subscriber;
import webSocketService.WebSocketClient;

/**
 * @Author: BLANCO CAAMANO, Ramon <ramonblancocaamano@gmail.com>
 */
public class TopicManagerStub implements TopicManager {

    public String user;

    public TopicManagerStub(String user) {
        WebSocketClient.newInstance();
        this.user = user;
    }

    @Override
    public void close() {
        WebSocketClient.close();
    }

    @Override
    public Publisher addPublisherToTopic(Topic topic) {
        Publisher publisher;

        apiREST_TopicManager.addPublisherToTopic(topic);
        publisher = new PublisherStub(topic);
        return publisher;
    }

    @Override
    public void removePublisherFromTopic(Topic topic) {
        apiREST_TopicManager.removePublisherFromTopic(topic);
    }

    @Override
    public Topic_check isTopic(Topic topic) {
        Topic_check check;

        check = apiREST_TopicManager.isTopic(topic);
        return check;
    }

    @Override
    public List<Topic> topics() {
        List<Topic> topics;

        topics = apiREST_TopicManager.topics();
        return topics;
    }

    @Override
    public Subscription_check subscribe(Topic topic, Subscriber subscriber) {
        Subscription_check subs_check;
        Topic_check topic_check;

        topic_check = isTopic(topic);
        if (topic_check.isOpen == false) {
            subs_check = new Subscription_check(topic, Subscription_check.Result.NO_TOPIC);
            return subs_check;
        }

        WebSocketClient.addSubscriber(topic, subscriber);
        subs_check = new Subscription_check(topic, Subscription_check.Result.OKAY);
        return subs_check;
    }

    @Override
    public Subscription_check unsubscribe(Topic topic, Subscriber subscriber) {
        Subscription_check subs_check;
        Topic_check topic_check;

        topic_check = isTopic(topic);
        if (topic_check.isOpen == false) {
            subs_check = new Subscription_check(topic, Subscription_check.Result.NO_TOPIC);
            return subs_check;
        }

        WebSocketClient.removeSubscriber(topic);
        subs_check = new Subscription_check(topic, Subscription_check.Result.NO_SUBSCRIPTION);
        return subs_check;
    }

}
