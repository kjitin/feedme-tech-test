package com.group.stars.consumer;

import com.group.stars.domain.Event;
import com.group.stars.domain.MessageFeed;
import com.group.stars.domain.MessageType;
import com.group.stars.transformer.MessageTransformer;
import com.group.stars.transformer.Transformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;


@Component
@Slf4j
public class FeedMeConsumer {

    private BufferedReader bufferedReader;

    private Transformer transformer;



    @Autowired
    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Autowired
    public void setTransformer(Transformer transformer) {
        this.transformer = transformer;
    }

    public void read() throws IOException {
        String inputLine;


        int eventCount =0;
        Event event = null;

        MessageTransformer messageTransformer = new MessageTransformer();
        while ((inputLine = bufferedReader.readLine()) != null) {

            MessageFeed messageFeed = messageTransformer.transformStrToMessage(inputLine);


            if (messageFeed.getType().equals(MessageType.event)) {
                if (eventCount > 0) {
                    log.info("Save Event to DB {}", event);
                }
                event = transformer.transformMessageToEvent(messageFeed);
                eventCount++ ;

            } else if (messageFeed.getType().equals(MessageType.market)) {
                transformer.transformMessageToMarket(messageFeed);
            } else {
                transformer.transformMessgeToOutcome(messageFeed);
            }



        }
    }

}
