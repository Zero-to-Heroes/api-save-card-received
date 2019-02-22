package com.zerotoheroes;

import org.assertj.core.api.WithAssertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicTest implements WithAssertions {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Ignore
    public void test() throws Exception {
        String jsonRequest = buildJsonRequest();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonRequest, headers);
        String url = "http://localhost:" + this.port + "/cardstats";

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private String buildJsonRequest() {
        return "{\n"
                + "\t\"creationDate\": \"2018-10-08T08:04:30.555\",\n"
                + "\t\"userId\": \"fakeUserId\",\n"
                + "\t\"userMachineId\": \"fakeUserMachineId\",\n"
                + "\t\"userName\": \"fakeUserName\",\n"
                + "\t\"setId\": \"boomsday\",\n"
                + "\t\"cardId\": \"card1Id\",\n"
                + "\t\"type\": \"NORMAL\",\n"
                + "\t\"rarity\": \"common\",\n"
                + "\t\"isNew\": \"true\"\n"
                + "}";
    }
}
