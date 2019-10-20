package com.group.stars.transformer;

import com.group.stars.domain.Event;
import com.group.stars.domain.Market;
import com.group.stars.domain.MessageFeed;
import com.group.stars.domain.Outcome;

import java.util.List;
import java.util.Map;

public class Transformer {


    private Map<String, Event> eventMap;

    private Map<String, Market> marketMap;

    public Transformer(Map<String, Event> eventMap, Map<String, Market> marketMap) {

        this.eventMap = eventMap;
        this.marketMap = marketMap;
    }

    public Event transformMessageToEvent(MessageFeed messageFeed) {

        List<String> body = messageFeed.getBody();


        Event event = new Event(body.get(0),
                body.get(1),
                body.get(2),
                body.get(3),
                Long.valueOf(body.get(4)),
                convertToBoolean(body.get(5)),
                convertToBoolean(body.get(6)));

        eventMap.put(event.getEventId(), event);

        return event;

    }


    public Market transformMessageToMarket(MessageFeed messageFeed) {
        List<String> body = messageFeed.getBody();
        Market market = new Market(body.get(0),
                                   body.get(1),
                                   body.get(2),
                                   convertToBoolean(body.get(3)),
                                   convertToBoolean(body.get(4)));

        marketMap.put(market.getMarketId(), market);
        Event event = eventMap.get(market.getEventId());
        if (event != null) {
            event.addToMarket(market);
        }
        return market;

    }

    public Outcome transformMessgeToOutcome(MessageFeed messageFeed) {
        List<String> body = messageFeed.getBody();
        Outcome outcome = new Outcome(body.get(0),
                body.get(1),
                body.get(2),
                body.get(3),
                convertToBoolean(body.get(4)),
                convertToBoolean(body.get(5)));

        Market market = marketMap.get(outcome.getMarketId());
        if (market != null) {
            market.addToOutcome(outcome);
        }

        return outcome;

    }


    private boolean convertToBoolean(String value) {
        if ("1".equals(value)) {
            return true;
        }
        return false;
    }
}
