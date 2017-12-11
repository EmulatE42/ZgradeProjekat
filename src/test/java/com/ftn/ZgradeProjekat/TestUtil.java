package com.ftn.ZgradeProjekat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/*
 * Created by djuro on 12/9/2017.
 */
public class TestUtil {

    public static String json(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper.writeValueAsString(object);
    }
}
