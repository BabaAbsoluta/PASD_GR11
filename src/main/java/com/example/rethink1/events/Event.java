package com.example.rethink1.events;

import org.springframework.context.ApplicationEvent;

public class Event extends ApplicationEvent {
    private String message;

    public Event(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
