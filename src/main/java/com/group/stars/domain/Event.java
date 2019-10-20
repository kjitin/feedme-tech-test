package com.group.stars.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Event {

    private final String eventId;

    private final String category;

    private final String subCategory;

    private final String name;

    private final Long startTime;

    private final boolean displayed;

    private final boolean suspended;

    private List<Market> markets = new ArrayList<>();


    public void addToMarket(Market market) {
        markets.add(market);
    }

}
