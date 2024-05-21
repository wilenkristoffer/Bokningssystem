package org.example.bokningssystem.services.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.bokningssystem.modell.BlackList;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class BlackListServiceImpl {


    private  HttpClient httpClient = HttpClient.newHttpClient();
    ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    public BlackListServiceImpl(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }
    public BlackListServiceImpl(){

    }


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

            BlackList existingEntry = getBlackListByEmail(email);

            if (existingEntry != null) {

                existingEntry.setOk(false);

                String jsonPayload = objectMapper.writeValueAsString(existingEntry);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("https://javabl.systementor.se/api/skt/blacklist/" + email))
                        .header("Content-Type", "application/json")
                        .PUT(HttpRequest.BodyPublishers.ofString(jsonPayload, StandardCharsets.UTF_8))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


                if (response.statusCode() == 200) {
                    System.out.println("Email status updated successfully.");
                } else {
                    System.err.println("Failed to update email status: " + response.body());
                }
            } else {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error occurred while adding email to blacklist.");
        }
    }



    private BlackList getBlackListByEmail(String email) throws URISyntaxException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://javabl.systementor.se/api/skt/blacklist"))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            BlackList[] blacklistEntries = objectMapper.readValue(response.body(), BlackList[].class);
            return Arrays.stream(blacklistEntries)
                    .filter(entry -> entry.getEmail().equalsIgnoreCase(email))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeEmailFromBlackList(String email, boolean newStatus) {
        try {
            String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8.toString());

            String url = "https://javabl.systementor.se/api/skt/blacklist/" + encodedEmail;

            BlackList updatedEntry = new BlackList();
            updatedEntry.setEmail(email);
            updatedEntry.setOk(newStatus);

            String jsonPayload = objectMapper.writeValueAsString(updatedEntry);


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Email status updated successfully.");
            } else {
                System.err.println("Failed to update email status: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error occurred while updating email status.");
        }
    }
}