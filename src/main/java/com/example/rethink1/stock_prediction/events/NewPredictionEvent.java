package com.example.rethink1.stock_prediction.events;

import org.springframework.context.ApplicationEvent;

public class NewPredictionEvent extends ApplicationEvent {
    private String message;

    public NewPredictionEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
