package com.group.stars.transformer;

import com.group.stars.domain.Event;
import com.group.stars.domain.Market;
import com.group.stars.domain.MessageFeed;
import com.group.stars.domain.Outcome;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class TransformerTest {


    public final static String INPUT_EVENT = "|1|create|event|1571155550834|ffccd4b8-38cd-4c75-9952-428a79310101|Football|Sky Bet Championship|\\|Norwich\\| vs \\|Aston Villa\\||1571155583774|0|1|";

    public final static String INPUT_MARKET = "|2|create|market|1571155550836|ffccd4b8-38cd-4c75-9952-428a79310101|f2782fd7-c6eb-46f1-b84b-4520335dd09f|Full Time Result|0|1|";

    public final static String INPUT_OUTCOME1 = "3|create|outcome|1571155550836|f2782fd7-c6eb-46f1-b84b-4520335dd09f|238db4ca-7350-4eca-a38e-feaa969ac1ab|\\|Norwich\\||4/6|0|1|";
    public final static String INPUT_OUTCOME2 = "4|create|outcome|1571155550836|f2782fd7-c6eb-46f1-b84b-4520335dd09f|08fc938d-a246-484d-8035-d7656bd2a442|Draw|7/2|0|1|";

    @Test
    public void testEventTransformIsSuccessful() {

        MessageTransformer messageTransformer = new MessageTransformer();
        MessageFeed messageFeed = messageTransformer.transformStrToMessage(INPUT_EVENT);
        Map<String, Event> eventMap  = new HashMap<>();

        Map<String, Market> marketMap  = new HashMap<>();


        Transformer transformer = new Transformer(eventMap, marketMap);

        Event event = transformer.transformMessageToEvent(messageFeed);

        Assert.assertEquals("Football", event.getCategory());
        Assert.assertEquals(false, event.isDisplayed());
        Assert.assertEquals(true, event.isSuspended());
    }


    @Test
    public void testMarketTransformIsSuccessful() {

        MessageTransformer messageTransformer = new MessageTransformer();
        MessageFeed messageFeed = messageTransformer.transformStrToMessage(INPUT_EVENT);

        MessageFeed messageFeedMarket = messageTransformer.transformStrToMessage(INPUT_MARKET);

        Map<String, Event> eventMap  = new HashMap<>();

        Map<String, Market> marketMap  = new HashMap<>();


        Transformer transformer = new Transformer(eventMap, marketMap);

        Event event = transformer.transformMessageToEvent(messageFeed);

        Market market = transformer.transformMessageToMarket(messageFeedMarket);

        Assert.assertEquals("Football", event.getCategory());
        Assert.assertEquals(false, event.isDisplayed());
        Assert.assertEquals(true, event.isSuspended());

        Assert.assertFalse(event.getMarkets().isEmpty());

        assertThat(event.getMarkets())
                .extracting("eventId")
                .contains("ffccd4b8-38cd-4c75-9952-428a79310101");

        Assert.assertEquals("ffccd4b8-38cd-4c75-9952-428a79310101", market.getEventId());
    }


    @Test
    public void testOutcomeTransformIsSuccessful() {

        MessageTransformer messageTransformer = new MessageTransformer();
        MessageFeed messageFeed = messageTransformer.transformStrToMessage(INPUT_EVENT);

        MessageFeed messageFeedMarket = messageTransformer.transformStrToMessage(INPUT_MARKET);

        MessageFeed messageFeedOutcome1 = messageTransformer.transformStrToMessage(INPUT_OUTCOME1);
        MessageFeed messageFeedOutcome2 = messageTransformer.transformStrToMessage(INPUT_OUTCOME2);

        Map<String, Event> eventMap  = new HashMap<>();

        Map<String, Market> marketMap  = new HashMap<>();


        Transformer transformer = new Transformer(eventMap, marketMap);

        Event event = transformer.transformMessageToEvent(messageFeed);

        Market market = transformer.transformMessageToMarket(messageFeedMarket);
        Outcome outcome1 = transformer.transformMessgeToOutcome(messageFeedOutcome1);
        Outcome outcome2 = transformer.transformMessgeToOutcome(messageFeedOutcome2);

        Assert.assertEquals("Football", event.getCategory());
        Assert.assertEquals(false, event.isDisplayed());
        Assert.assertEquals(true, event.isSuspended());

        Assert.assertFalse(event.getMarkets().isEmpty());

        assertThat(event.getMarkets())
                .extracting("eventId")
                .contains("ffccd4b8-38cd-4c75-9952-428a79310101");


        assertThat(market.getOutcomes())
                .extracting("name")
                .contains("Norwich","Draw");

        Assert.assertEquals("ffccd4b8-38cd-4c75-9952-428a79310101", market.getEventId());
    }

}