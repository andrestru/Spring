package co.com.ias.learning.students.infrastructure.Codecs.Json;

import co.com.ias.learning.students.application.domain.IdentificationNumber;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class NonEmptyIdentificationJson {

    public static class NonEmptyIdentificationEncode extends JsonSerializer<IdentificationNumber>{

        @Override
        public void serialize(IdentificationNumber identificationNumber, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(identificationNumber.getValue());
        }
    }

    public static class NonemptyIdentificationDecode extends JsonDeserializer<IdentificationNumber>{

        @Override
        public IdentificationNumber deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            final String json = jsonParser.getValueAsString();
            return new IdentificationNumber(json);
        }
    }

}
