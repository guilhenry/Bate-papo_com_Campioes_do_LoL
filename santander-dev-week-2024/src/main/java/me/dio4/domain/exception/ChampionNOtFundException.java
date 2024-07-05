package me.dio4.domain.exception;

public class ChampionNOtFundException extends RuntimeException {

    public ChampionNOtFundException(Long championId){
        super("Champion %d not found.".formatted(championId));
    }

}
