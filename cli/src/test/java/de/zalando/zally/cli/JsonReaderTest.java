package de.zalando.zally.cli;

import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.JsonValue;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.Test;


public class JsonReaderTest {
    private static final String fixture = "{\"swagger\": \"2.0\",\"info\": {\"title\": \"Product Service\"}}";

    @Test
    public void returnJsonValueWhenEverythingIsOk() {
        JsonReader reader = new JsonReader(getFixtureReader());
        JsonValue value = reader.read();

        assertEquals("2.0", value.asObject().get("swagger").asString());
        assertEquals("Product Service", value.asObject().get("info").asObject().get("title").asString());
    }

    private InputStreamReader getFixtureReader() {
        InputStream inputStream = new ByteArrayInputStream(fixture.getBytes());
        return new InputStreamReader(inputStream);
    }
}