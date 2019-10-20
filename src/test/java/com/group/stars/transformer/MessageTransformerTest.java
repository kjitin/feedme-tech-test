package com.group.stars.transformer;

import com.group.stars.domain.MessageFeed;
import com.group.stars.domain.MessageType;
import org.junit.Assert;
import org.junit.Test;


public class MessageTransformerTest {


    @Test
    public void testEventMessageIsConverted() {
        String input = "|1|create|event|1571155550834|ffccd4b8-38cd-4c75-9952-428a79310101|Football|Sky Bet Championship|\\|Norwich\\| vs \\|Aston Villa\\||1571155583774|0|1|";


        MessageTransformer messageTransformer = new MessageTransformer();
        MessageFeed messageFeed = messageTransformer.transformStrToMessage(input);


        Assert.assertTrue(messageFeed.getMsgId().equals(1));
        Assert.assertTrue(messageFeed.getType().equals(MessageType.event));

    }

    @Test
    public void testMarketMessageIsConverter() {
        String input = "|2|create|market|1571155550836|ffccd4b8-38cd-4c75-9952-428a79310101|f2782fd7-c6eb-46f1-b84b-4520335dd09f|Full Time Result|0|1|";

        MessageTransformer messageTransformer = new MessageTransformer();
        MessageFeed messageFeed = messageTransformer.transformStrToMessage(input);


        Assert.assertTrue(messageFeed.getMsgId().equals(2));
        Assert.assertTrue(messageFeed.getType().equals(MessageType.market));
    }

    @Test
    public void testOutcomeMessageIsConverteed() {
        String input ="|3|create|outcome|1571155550836|f2782fd7-c6eb-46f1-b84b-4520335dd09f|238db4ca-7350-4eca-a38e-feaa969ac1ab|\\|Norwich\\||4/6|0|1|";

        MessageTransformer messageTransformer = new MessageTransformer();
        MessageFeed messageFeed = messageTransformer.transformStrToMessage(input);


        Assert.assertTrue(messageFeed.getMsgId().equals(3));
        Assert.assertTrue(messageFeed.getType().equals(MessageType.outcome));
    }
}
