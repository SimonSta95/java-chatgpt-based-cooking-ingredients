package com.example.chatgptbasedcookingingredients;

import com.example.chatgptbasedcookingingredients.model.OpenAiMessage;
import com.example.chatgptbasedcookingingredients.model.OpenAiRequest;
import com.example.chatgptbasedcookingingredients.model.OpenAiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service

public class IngredientService {

    private final RestClient restClient;

    public IngredientService(@Value("${BASE_URL}") String baseUrl,
                             @Value("${API_KEY}") String key) {
        restClient = RestClient.builder()
                .defaultHeader("Authorization", "Bearer " + key)
                .baseUrl(baseUrl)
                .build();
    }

    public String getAnswerFromOpenAi(String question) {
        OpenAiRequest request = new OpenAiRequest("gpt-4o-mini",
                List.of(new OpenAiMessage("user", question)), 0.1);

        OpenAiResponse response = restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(OpenAiResponse.class);

        return response.getAnswer();
    }
}
