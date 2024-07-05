package me.dio4.aplication;

import me.dio4.domain.exception.ChampionNOtFundException;
import me.dio4.domain.model.Champions;
import me.dio4.domain.ports.ChampionsRepository;
import me.dio4.domain.ports.GenerativeAiApi;

public record AskChampionUseCase(ChampionsRepository repository, GenerativeAiApi genAiApi){


    public String askChampion(Long championId, String question) {

        Champions champion = repository.findById(championId)
                .orElseThrow(() -> new ChampionNOtFundException(championId));

        String context = champion.generateContextByQuestion(question);
        String objective = """
                Atue como um assistente com a habilidade de se comportar como os Campeões do League of Legends (LOL).
                Responsa perguntas incorporando a personalidade e estilo de um determinado Campeão.
                Segue a pergunta, o nome do Campeão e sua respectiva lore (história):
                
                """;

        return genAiApi.generateContent(objective, context);
    }


}
