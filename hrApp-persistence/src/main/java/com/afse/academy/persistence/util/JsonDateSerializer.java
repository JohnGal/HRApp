package com.afse.academy.persistence.util;

import org.apache.commons.lang3.time.FastDateFormat;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

public class JsonDateSerializer extends JsonSerializer<Date> {
    private static final FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy-MM-dd", Locale.UK);

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {

        String formattedDate = dateFormat.format(date);

        gen.writeString(formattedDate);

    }
}
