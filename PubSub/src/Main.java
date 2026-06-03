import consumer.Consumer;
import consumer.PrintConsumer;
import filter.EventTypeFilter;
import service.PubSubService;

public class Main {

    public static void main(String[] args) throws Exception {

        PubSubService service = new PubSubService();

        service.createTopic("orders");

        Consumer c1 = new PrintConsumer("consumer-1");

        Consumer c2 = new PrintConsumer("consumer-2");

        service.subscribe("orders", c1, null);

        service.subscribe("orders", c2, new EventTypeFilter("PAYMENT"));

        service.publish("orders", "ORDER_CREATED");

        service.publish("orders", "PAYMENT_SUCCESS");

        service.publish("orders", "ORDER_SHIPPED");
    }
}