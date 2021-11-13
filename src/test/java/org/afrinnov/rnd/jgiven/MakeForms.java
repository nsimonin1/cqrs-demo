package org.afrinnov.rnd.jgiven;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MakeForms {
    public static String prepareParam(String field, String value) throws Exception {
        return String.format("%s=%s",
                URLEncoder.encode(field, StandardCharsets.UTF_8.name()),
                URLEncoder.encode(value, StandardCharsets.UTF_8.name()));
    }
}
