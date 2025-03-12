package com.reinertisa.supapi.event;

import com.reinertisa.supapi.entity.UserEntity;
import com.reinertisa.supapi.enumeration.EventType;

import java.util.Map;

public class UserEvent {
    private UserEntity user;
    private EventType type;
    private Map<?, ?> data;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Map<?, ?> getData() {
        return data;
    }

    public void setData(Map<?, ?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "user=" + user +
                ", type=" + type +
                ", data=" + data +
                '}';
    }
}
