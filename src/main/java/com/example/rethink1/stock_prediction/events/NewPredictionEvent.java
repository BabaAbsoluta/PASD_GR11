package com.example.rethink1.stock_prediction.events;

import org.springframework.context.ApplicationEvent;

public class NewPredictionEvent extends ApplicationEvent {
    public NewPredictionEvent(Object source) {
        super(source);
    }

    public String toString() {
        return "new_prediction_event";
    }
}
