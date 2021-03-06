package de.zalando.zally;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(properties = "zally.message=")
public class RestApiEmptyMessageTest extends RestApiBaseTest {
    @Test
    public void shouldNotContainMessageFieldWhenMessageIsEmpty() throws IOException {
        ResponseEntity<JsonNode> responseEntity = sendRequest(
                new ObjectMapper().readTree(ResourceUtils.getFile("src/test/resources/fixtures/api_spp.json")));
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        JsonNode rootObject = responseEntity.getBody();
        assertThat(rootObject.has("violations")).isTrue();

        assertThat(rootObject.has("message")).isFalse();
    }
}
