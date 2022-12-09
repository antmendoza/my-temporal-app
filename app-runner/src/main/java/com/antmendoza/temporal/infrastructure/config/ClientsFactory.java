package com.antmendoza.temporal.infrastructure.config;

import com.antmendoza.ListPendingTaskServiceImpl;
import com.antmendoza.api.ListPendingTaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientsFactory {

    @Bean
    public ListPendingTaskService listPendingTaskService() {
        return new ListPendingTaskServiceImpl();
    }

}
