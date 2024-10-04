package br.manodo.loto.loto.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 
public class SwaggerConfig {

    @Bean
    public Docket docket(){  

         return new Docket(DocumentationType.SWAGGER_2)
                    .useDefaultResponseMessages(false)
                    .select()
                    .apis(RequestHandlerSelectors
                                       .basePackage("br.manodo.loto.loto.controller"))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(apiInfo());  
    }

    private ApiInfo apiInfo(){     

        return new ApiInfoBuilder()                        
                        .title("Verificação Lotus")
                        .description("Velocidade final da Lotus")
                        .version("1.0")                        
                        .contact(contato())
                        .build();         
    }


    private Contact contato(){
        return new Contact("Joaozinho vinte",
                           "http://www.joaozinho20.com.br",
                           "joaozinho20@gmail.com");
    }    
}
