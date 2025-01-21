package com.sg.kata.bankaccount.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger Configuration
 */
@Configuration
public class SwaggerConfig {
	public static final String BANK_ACCOUNT = "Bank Account Kata";

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Bank Account API").version("1.0").description(
				"Make withdrawals and deposits, get your balance from your bank account.")
				.contact(new Contact().name("Support Team").email("elassaassi@gmail.com")))
				.addTagsItem(new Tag().name(BANK_ACCOUNT).description(
						"Make withdrawals and deposits, get your balance and see your operations history from your bank account."));
	}
}
