package com.NextGenCommerce;

import com.NextGenCommerce.tools.ShoppingCartMCPService;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class NextGenCommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextGenCommerceApplication.class, args);
	}

    //agent should be up always
    //This follows a agent to spring boot  and spring boot to agent
    @Bean
    public List<ToolCallback> shoppingCartToolCallback(ShoppingCartMCPService cartMCPService){
        return List.of(ToolCallbacks.from(cartMCPService));
    }

    //Note Whenever im running  this these all the prompt tools(apis) exposed to the outside
}
