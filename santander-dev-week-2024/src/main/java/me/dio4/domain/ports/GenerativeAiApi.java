package me.dio4.domain.ports;

public interface GenerativeAiApi {

    String generateContent(String objective, String context);
}
