package me.dio4.adapters.in;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio4.aplication.ListChampionsUseCase;
import me.dio4.domain.model.Champions;


@Tag(name = "Campeões", description = "Endpoints do domínio de Campeões do LOL.")
@RestController
@RequestMapping("/champions")
public record ListChampionsRestController(ListChampionsUseCase useCase) {

    @CrossOrigin
    @GetMapping
    public List<Champions> findAllChampions() {
        return useCase.findAll();
    }
}
