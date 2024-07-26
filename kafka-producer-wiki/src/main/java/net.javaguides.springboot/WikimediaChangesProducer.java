package net.javaguides.springboot;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import com.launchdarkly.eventsource.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventSource;


@Service
public class WikimediaChangesProducer {

    private static Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(){
        String topic = "wikimedia_recentchange";

        //to read real-time data
        EventHandler eventHandler= new WikimediaChangesHandler(kafkaTemplate,topic);
        String url = "";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventsource = builder.build();
        eventsource.start();

        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            LOGGER.error("Thread interrupted during sleep", e);
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }
    
}
