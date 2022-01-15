package com.example.rethink1.stock_prediction.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class NewPredictionEventPublisher{
    @Autowired
    private ApplicationEventPublisher publisher;

    public void setApplicationEventPublisher (ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    public void publishEvent(final String message) {
        System.out.println("Publishing event " + message + ".\n");
        NewPredictionEvent newPredictionEvent = new NewPredictionEvent(this, message);
        publisher.publishEvent(newPredictionEvent);
    }
}
