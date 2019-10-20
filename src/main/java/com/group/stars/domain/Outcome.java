package com.group.stars.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Outcome {

    private final String marketId;

    private final String outcomeId;

    private final String name;

    private final String price;

    private final boolean displayed;

    private final boolean suspended;
}
