package org.adurlea.spring.boot.ws.tools;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adurlea on 02/12/18.
 */
public class CustomDateDeserializer extends StdDeserializer<Date> {

    private static SimpleDateFormat formatter
            = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    public CustomDateDeserializer() {
        super(Date.class);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        String jsonDate = jsonParser.getText();
        if (StringUtils.isNotBlank(jsonDate)) {
            try {
                return formatter.parse(jsonDate);
            } catch (ParseException e) {
                throw new IOException(e);
            }
        } else {
            return null;
        }

    }
}
