package me.dio4;


import me.dio4.aplication.AskChampionUseCase;
import me.dio4.aplication.ListChampionsUseCase;
import me.dio4.domain.ports.ChampionsRepository;
import me.dio4.domain.ports.GenerativeAiApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepository repository) {
		return new ListChampionsUseCase(repository);
	}

	@Bean
	public AskChampionUseCase provideAskChampionUseCase(ChampionsRepository repository,
														GenerativeAiApi genAiService) {
		return new AskChampionUseCase(repository, genAiService);
	}
}