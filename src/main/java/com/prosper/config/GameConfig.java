package com.prosper.config;

import com.prosper.dto.Table;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
public class GameConfig {

    @Autowired
    private ApplicationConfiguration configuration;

    @Bean
    public Table getTable() {
        return new Table(configuration.getRowSize(),configuration.getColSize())
                .empty()
                .fill(configuration.getExpectedCellTypes(), configuration.getComplexity());
    }
}
