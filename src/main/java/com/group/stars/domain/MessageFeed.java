package com.group.stars.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class MessageFeed {

    private Integer msgId;

    private String operation;

    private MessageType type;

    private Long timestamp;

    private List<String> body;

}
