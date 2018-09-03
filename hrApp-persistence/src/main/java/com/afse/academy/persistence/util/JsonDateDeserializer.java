package com.afse.academy.persistence.util;

import org.apache.commons.lang3.time.FastDateFormat;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class JsonDateDeserializer extends JsonDeserializer<Date> {
    private static final FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy-MM-dd", Locale.UK);

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        try {
            return dateFormat.parse(jsonParser.getText());
        } catch (ParseException e) {
            throw new JsonParseException("Could not parse date", jsonParser.getCurrentLocation(), e);
        }
    }
}
