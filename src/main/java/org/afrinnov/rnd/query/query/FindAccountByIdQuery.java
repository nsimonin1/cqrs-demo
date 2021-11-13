package org.afrinnov.rnd.query.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindAccountByIdQuery {
    private final String id;
}
