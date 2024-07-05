package me.dio4.adapters.out;


import feign.FeignException;
import feign.RequestInterceptor;
import me.dio4.domain.ports.GenerativeAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@ConditionalOnProperty(name = "generative-ai.provider", havingValue = "OPENAI")
@FeignClient(name = "openAiChatApi", url = "https://api.openai.com", configuration = OpenAiChatApi.Config.class)
public interface OpenAiChatApi extends GenerativeAiApi {

    @PostMapping("/v1/chat/completions")
    OpenAiChatCompletionResp chatCompletion(OpenAiChatCompletionReq request);

    @Override
    default String generateContent(String objective, String context){
        String model = "gpt-3.5-turbo";
        List<Message>messages = List.of(
                new Message("system", objective),
                new Message("user", context)
        );
        OpenAiChatCompletionReq req = new OpenAiChatCompletionReq(model, messages);

     try {
        OpenAiChatCompletionResp resp = chatCompletion(req);
        return resp.choices().getFirst().messages().content();
    } catch (FeignException httpErrors) {
        return "Deu ruim! Erro de comunicação com a API da OpenAI.";
    } catch (Exception unexpectedError) {
        return "Deu mais ruim ainda! O retorno da API da OpenAI não contem os dados esperados.";
    }}

    record  OpenAiChatCompletionReq(String model, List<Message>messages){ }
    record Message(String rule, String content){ }

    record OpenAiChatCompletionResp(List<Choice>choices){ }
    record Choice(Message messages){ }

    class Config{
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${openai.api-key}") String apiKey){
            return requestTemplate -> requestTemplate.header(
                    HttpHeaders.AUTHORIZATION,"Bearer %s".formatted(apiKey));
        }
    }
}
