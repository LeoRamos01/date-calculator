package lramos.datecalculator.config;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/people.*|/person.*|"))
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		@SuppressWarnings("rawtypes")
		ArrayList<VendorExtension> vendorExtensions = new ArrayList<>();

		ApiInfo apiInfo = new ApiInfo("Calculadora de datas", "Projeto para cálculos com datas", "1",
				"https://... URL for the Terms and Services",
				new Contact("Leonardo Ramos", "https://github.com/LeoRamos01", "drleo111@gmail.com"), "a License of API",
				"https://... URL for the license", vendorExtensions);
		
		return apiInfo;
	}

}