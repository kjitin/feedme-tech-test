package com.group.stars.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class MessageFeedTest {


    @Test
    public void testEventMessageIsConverted() {
        String input = "|1|create|event|1571155550834|ffccd4b8-38cd-4c75-9952-428a79310101|Football|Sky Bet Championship|\\|Norwich\\| vs \\|Aston Villa\\||1571155583774|0|1|";

        String firstPass = input.replaceAll("[\\\\|]","~");
        String secondPass = firstPass.replaceAll("~~","");
        String[] eventInfo = secondPass.split("~");

        List<String> filteredFeed = Arrays.asList(eventInfo).stream()
                                          .filter(f-> !f.isEmpty())
                                          .collect(Collectors.toList());

        Assert.assertEquals("1",filteredFeed.get(0));
        Assert.assertEquals("event",filteredFeed.get(2));

        List<String> body = filteredFeed.subList(3,filteredFeed.size());

        MessageFeed messageFeed = new MessageFeed(
                                      Integer.parseInt(filteredFeed.get(0)),
                                      filteredFeed.get(1),
                                      MessageType.valueOf(filteredFeed.get(2)),
                                      Long.valueOf(filteredFeed.get(3)),
                                      body);

        Assert.assertTrue(messageFeed.getMsgId().equals(1));
        Assert.assertTrue(messageFeed.getType().equals(MessageType.event));

    }

    @Test
    public void testMarketMessageIsConverter() {
        String input = "|2|create|market|1571155550836|ffccd4b8-38cd-4c75-9952-428a79310101|f2782fd7-c6eb-46f1-b84b-4520335dd09f|Full Time Result|0|1|";

        String firstPass = input.replaceAll("[\\\\|]","~");
        String secondPass = firstPass.replaceAll("~~","");
        String[] eventInfo = secondPass.split("~");

        List<String> filteredFeed = Arrays.asList(eventInfo).stream()
                .filter(f-> !f.isEmpty())
                .collect(Collectors.toList());

        Assert.assertEquals("2",filteredFeed.get(0));
        Assert.assertEquals("market",filteredFeed.get(2));

        List<String> body = filteredFeed.subList(3,filteredFeed.size());

        MessageFeed messageFeed = new MessageFeed(
                Integer.parseInt(filteredFeed.get(0)),
                filteredFeed.get(1),
                MessageType.valueOf(filteredFeed.get(2)),
                Long.valueOf(filteredFeed.get(3)),
                body);

        Assert.assertTrue(messageFeed.getMsgId().equals(2));
        Assert.assertTrue(messageFeed.getType().equals(MessageType.market));
    }

    @Test
    public void testOutcomeMessageIsConverteed() {
        String input ="|3|create|outcome|1571155550836|f2782fd7-c6eb-46f1-b84b-4520335dd09f|238db4ca-7350-4eca-a38e-feaa969ac1ab|\\|Norwich\\||4/6|0|1|";

        String firstPass = input.replaceAll("[\\\\|]","~");
        String secondPass = firstPass.replaceAll("~~","");
        String[] eventInfo = secondPass.split("~");

        List<String> filteredFeed = Arrays.asList(eventInfo).stream()
                .filter(f-> !f.isEmpty())
                .collect(Collectors.toList());

        Assert.assertEquals("3",filteredFeed.get(0));
        Assert.assertEquals("outcome",filteredFeed.get(2));

        List<String> body = filteredFeed.subList(3,filteredFeed.size());

        MessageFeed messageFeed = new MessageFeed(
                Integer.parseInt(filteredFeed.get(0)),
                filteredFeed.get(1),
                MessageType.valueOf(filteredFeed.get(2)),
                Long.valueOf(filteredFeed.get(3)),
                body);

        Assert.assertTrue(messageFeed.getMsgId().equals(3));
        Assert.assertTrue(messageFeed.getType().equals(MessageType.outcome));
    }
}
