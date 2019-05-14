package publisher;

import apiREST.apiREST_Publisher;
import util.Message;
import util.Topic;

/**
 * @Author: BLANCO CAAMANO, Ramon <ramonblancocaamano@gmail.com>
 */
public class PublisherStub implements Publisher {

    Topic topic;

    public PublisherStub(Topic topic) {
        this.topic = topic;
    }

    @Override
    public void publish(Message message) {
        apiREST_Publisher.publish(message);
    }

}
