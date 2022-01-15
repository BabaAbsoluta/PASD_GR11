package com.example.rethink1.stock_prediction.events.publisher;

import com.example.rethink1.stock_prediction.events.NewPredictionEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class NewPredictionEventPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    public void setApplicationEventPublisher (ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    public void publish() {
        NewPredictionEvent predictionEvent = new NewPredictionEvent(this);
        publisher.publishEvent(predictionEvent);
    }
}
