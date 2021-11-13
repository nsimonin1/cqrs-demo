package org.afrinnov.rnd.common.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseEvent<T> {
    private final T id;
}
