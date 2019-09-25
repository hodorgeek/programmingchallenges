package com.vwits.asid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

public class TWTest {

    private String BASE_URL = "https://http-hunt.thoughtworks-labs.net/challenge";

    private ObjectMapper objectMapper = new ObjectMapper();

    private HttpHeaders httpHeaders;

    private String userIdValue = "Idh7KYMtz";

    private String inputURL = BASE_URL + "/input";
    private String outputURL = BASE_URL + "/output";

    private RestTemplate restTemplate = new RestTemplate();
    private HttpEntity requestGetEntity;
    private HttpEntity requestPostEntity;


    @Before
    public void setUp() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add("userId", userIdValue);
        requestGetEntity = new HttpEntity<>(httpHeaders);
    }

    @Test
    public void test_stage1_API() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.exchange(inputURL, HttpMethod.GET, requestGetEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        final Input input = objectMapper.readValue(responseEntity.getBody(), Input.class);

        Stage1Output output = new Stage1Output();
        output.setCount(countCharacter(input.getText()));

        final String jsonBody = objectMapper.writeValueAsString(output);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        requestPostEntity = new HttpEntity<>(jsonBody, httpHeaders);
        final ResponseEntity<String> postResponse = restTemplate.postForEntity(outputURL, requestPostEntity, String.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
    }

    @Test
    public void test_stage2_API() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.exchange(inputURL, HttpMethod.GET, requestGetEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        final Input input = objectMapper.readValue(responseEntity.getBody(), Input.class);

        Stage2Output output = new Stage2Output();
        output.setCount(countWord(input.getText()));

        final String jsonBody = objectMapper.writeValueAsString(output);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        requestPostEntity = new HttpEntity<>(jsonBody, httpHeaders);
        final ResponseEntity<String> postResponse = restTemplate.postForEntity(outputURL, requestPostEntity, String.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
    }

    @Test
    public void test_stage3_API() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.exchange(inputURL, HttpMethod.GET, requestGetEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        final Input input = objectMapper.readValue(responseEntity.getBody(), Input.class);

        Stage3Output output = new Stage3Output();
        output.setCount(countSentence(input.getText()));

        final String jsonBody = objectMapper.writeValueAsString(output);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        requestPostEntity = new HttpEntity<>(jsonBody, httpHeaders);
        final ResponseEntity<String> postResponse = restTemplate.postForEntity(outputURL, requestPostEntity, String.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
    }

    @Test
    public void test_stage4_API() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.exchange(inputURL, HttpMethod.GET, requestGetEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        final Input input = objectMapper.readValue(responseEntity.getBody(), Input.class);

        final String jsonBody = objectMapper.writeValueAsString(countVowels(input.getText()));
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        requestPostEntity = new HttpEntity<>(jsonBody, httpHeaders);
        final ResponseEntity<String> postResponse = restTemplate.postForEntity(outputURL, requestPostEntity, String.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
    }

    private long countCharacter(final String text) {
        return text.chars().count();
    }

    private long countWord(final String text) {
       return text.split(" ").length;
    }

    private long countSentence(final String text) {
        int sentenceCount = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (i == text.length() - 1 && (ch == '.' || ch == '?')) {
                sentenceCount++;
            } else {
                if ((ch == '.' || ch == '?') && text.charAt(i + 1) == ' ') {
                    sentenceCount++;
                }
            }
        }
        return sentenceCount;
    }

    private Stage4Output countVowels(final String text) {
        Stage4Output stage4Output = new Stage4Output();
        text.chars().forEach(ch -> {
           switch (ch) {
               case 'a':
               case 'A':
                   stage4Output.a++;
                   break;
               case 'E':
               case 'e':
                   stage4Output.e++;
                   break;
               case 'I':
               case 'i':
                   stage4Output.i++;
                   break;
               case 'O':
               case 'o':
                   stage4Output.o++;
                   break;
               case 'u':
               case 'U':
                   stage4Output.u++;
                   break;
           }
        });
        return stage4Output;
    }
}




@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "stage",
        "statement",
        "instructions",
        "sampleInput",
        "sampleOutput"
})

@ToString
@Getter
@Setter
@NoArgsConstructor
class InputOutput {

    @JsonProperty("stage")
    public String stage;
    @JsonProperty("statement")
    public String statement;
    @JsonProperty("instructions")
    public String instructions;
    @JsonProperty("sampleInput")
    public SampleInput sampleInput;
    @JsonProperty("sampleOutput")
    public SampleOutput sampleOutput;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "text"
})

@ToString
@Getter
@Setter
@NoArgsConstructor
class Input {

    @JsonProperty("text")
    public String text;

}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "count"
})

@ToString
@Getter
@Setter
@NoArgsConstructor
class Stage1Output {

    @JsonProperty("count")
    public Long count;

}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "input"
})

@ToString
@Getter
@Setter
@NoArgsConstructor
class SampleInput {

    @JsonProperty("input")
    public Input input;

}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "output"
})

@ToString
@Getter
@Setter
@NoArgsConstructor
class SampleOutput {

    @JsonProperty("output")
    public Stage1Output output;

}


@ToString
@Getter
@Setter
@NoArgsConstructor
class Stage2Output {

    @JsonProperty("wordCount")
    public Long count;

}

@ToString
@Getter
@Setter
@NoArgsConstructor
class Stage3Output {

    @JsonProperty("sentenceCount")
    public Long count;

}

@ToString
@Getter
@Setter
@NoArgsConstructor
class Stage4Output {


    @JsonProperty("a")
    public int a;

    @JsonProperty("e")
    public int e;

    @JsonProperty("i")
    public int i;

    @JsonProperty("o")
    public int o;

    @JsonProperty("u")
    public int u;

}
