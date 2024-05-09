package org.example.bokningssystem.services.impl;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.bokningssystem.modell.BlackList;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

@Service
public class BlackListServiceImpl {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    public boolean isCustomerBlacklisted(String email) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://javabl.systementor.se/api/stefan/blacklist"))
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
}
