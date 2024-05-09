package org.example.bokningssystem.services.impl;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.bokningssystem.modell.BlackList;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class BlackListServiceImpl {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    public boolean isCustomerBlacklisted(String email) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://javabl.systementor.se/api/skt/blacklist"))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            BlackList[] blacklistEntries = objectMapper.readValue(response.body(), BlackList[].class);
            boolean isBlacklisted = Arrays.stream(blacklistEntries)
                    .anyMatch(entry -> entry.getEmail().equalsIgnoreCase(email) && !entry.isOk());

            return isBlacklisted;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addEmailToBlackList(String email) throws URISyntaxException {
        try {
            BlackList newEntry = new BlackList(
                    0,
                    email,
                    null,
                    null,
                    null,
                    false
            );

            String jsonPayload = objectMapper.writeValueAsString(newEntry);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://javabl.systementor.se/api/skt/blacklist"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


            if (response.statusCode() == 200) {
                System.out.println("Email added to remote blacklist successfully.");
            } else {
                System.err.println("Failed to add email to remote blacklist: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error occurred while adding email to blacklist.");
        }
    }
}
