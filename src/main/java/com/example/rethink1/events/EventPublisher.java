package com.example.rethink1.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

@Getter
@Setter
public class EventPublisher implements ApplicationEventPublisherAware {
    @Autowired
    private ApplicationEventPublisher publisher;
    private Event event;

    public void setApplicationEventPublisher (ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    public void publishEvent(final String message) {
        System.out.println("Publishing event " + message + ".\n");
        event = new Event(this, message);
        publisher.publishEvent(event);
    }
}
