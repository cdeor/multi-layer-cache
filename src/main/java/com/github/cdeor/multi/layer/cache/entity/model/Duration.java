package com.github.cdeor.multi.layer.cache.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
public class Duration {

    private long duration;
    private TimeUnit timeUnit;

    public long toSeconds() {
        return timeUnit.toSeconds(duration);
    }
}
