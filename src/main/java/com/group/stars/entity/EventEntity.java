package com.group.stars.entity;


import com.group.stars.domain.Market;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "Events")
public class EventEntity {

    @Id
    private String eventId;

    private String category;

    private String subCategory;

    private String name;

    private Long startTime;

    private boolean displayed;

    private boolean suspended;

    public EventEntity() {}

    private List<Market> markets = new ArrayList<>();

}
