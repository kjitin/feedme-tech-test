package com.group.stars.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Market {

    private final String eventId;

    private final String marketId;

    private final String name;

    private final boolean displayed;

    private final boolean suspended;

    private List<Outcome> outcomes = new ArrayList<>();


    public void addToOutcome(Outcome outcome) {
        outcomes.add(outcome);
    }

}
