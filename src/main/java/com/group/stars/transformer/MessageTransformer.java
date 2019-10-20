package com.group.stars.transformer;

import com.group.stars.domain.MessageFeed;
import com.group.stars.domain.MessageType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MessageTransformer {


    public MessageFeed transformStrToMessage(String input) {
        String firstPass = input.replaceAll("[\\\\|]","~");
        String secondPass = firstPass.replaceAll("~~","");
        String[] eventInfo = secondPass.split("~");

        List<String> filteredFeed = Arrays.asList(eventInfo).stream()
                .filter(f-> !f.isEmpty())
                .collect(Collectors.toList());


        List<String> body = filteredFeed.subList(4,filteredFeed.size());

        return new MessageFeed(
                Integer.parseInt(filteredFeed.get(0)),
                filteredFeed.get(1),
                MessageType.valueOf(filteredFeed.get(2)),
                Long.valueOf(filteredFeed.get(3)),
                body);
    }
}
