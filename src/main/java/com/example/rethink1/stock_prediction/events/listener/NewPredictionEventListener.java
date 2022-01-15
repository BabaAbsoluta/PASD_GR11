package com.example.rethink1.stock_prediction.events.listener;

import com.example.rethink1.stock_prediction.events.NewPredictionEvent;
import org.springframework.context.ApplicationListener;

public class NewPredictionEventListener implements ApplicationListener<NewPredictionEvent> {
    @Override
    public void onApplicationEvent(NewPredictionEvent event) {
        // what to do when event is triggered
        System.out.println(event.toString());
    }
}
