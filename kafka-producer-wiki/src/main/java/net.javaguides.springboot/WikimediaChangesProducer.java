import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WikimediaChangesProducer {

    private static Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private kafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(kafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(){
        String topic = "wikimedia_recentchange";

        //to read real-time data
        
    }
    
}
