package com.github.uuidcode.core.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.github.uuidcode.core.util.CoreUtil;

import static java.util.Optional.ofNullable;

/**
 * https://stackoverflow.com/questions/6852213/can-jackson-be-configured-to-trim-leading-trailing-whitespace-from-all-string-pr
 */
public class StringTrimModule extends SimpleModule {
    public static StringTrimModule of() {
        return new StringTrimModule();
    }

    public StringTrimModule() {
        addDeserializer(String.class, new StdScalarDeserializer<String>(String.class) {
            @Override
            public String deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
                String value = jsonParser.getValueAsString();
                return ofNullable(value)
                    .map(String::trim)
                    .orElse(null);
            }
        });

        addSerializer(String.class, new StdScalarSerializer<String>(String.class) {
            @Override
            public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
                if (CoreUtil.isEmpty(value)) {
                    gen.writeString((String) null);
                    return;
                }

                gen.writeString(value.trim());
            }
        });
    }
}
