package br.com.mksoftware.control.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer{
	
	
	@Bean
	public Docket apiDoket() {
		
		return new  Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis((RequestHandlerSelectors.basePackage("br.com.mksoftware.control.resources")))
				.build()
				.tags(new Tag("Departamento", "Gerencia Departamentos"))
				.apiInfo(this.apiInfo());
	}
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");

	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");

	}
	
	public ApiInfo apiInfo() {
		
		return new ApiInfoBuilder()
				.title("Control API")
				.description("Api de controle de usuários")
				.version("1")
				.contact(new Contact("MkSoftware", "https://www.mksoftware.com.br", "719915****"))
				.build();
	}

}
