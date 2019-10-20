package com.group.stars.service;

import com.group.stars.domain.Event;
import com.group.stars.entity.EventEntity;
import com.group.stars.repo.EventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {


    @Autowired
    private EventRepository eventRepository;

    public void save(Event event) {

        EventEntity eventEntity = new EventEntity();
        BeanUtils.copyProperties(event, eventEntity);
        eventRepository.save(eventEntity);
    }
}
