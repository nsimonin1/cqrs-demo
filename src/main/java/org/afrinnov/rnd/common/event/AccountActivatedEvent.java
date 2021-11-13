package org.afrinnov.rnd.common.event;

import lombok.Getter;

@Getter
public class AccountActivatedEvent extends BaseEvent<String> {
    private final String status;

    public AccountActivatedEvent(String id, String status) {
        super(id);
        this.status = status;
    }
}
