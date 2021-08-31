package com.elasticsearch;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan("com.elasticsearch")
//@EnableJpaRepositories("com.mongodb.dao.repository")
//@EnableMongoRepositories("com.mongodb.dao.repository")
//@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ElasticsearchCrudRepository.class))
@EnableElasticsearchRepositories(basePackages = "com.elasticsearch.dao.repository")
@SpringBootApplication
public class ElasticSearchSpringBootApplication {

	
	@Autowired
	private Environment environment;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SpringApplication.run(ElasticSearchSpringBootApplication.class, args);

	}

	@Bean
    public RestHighLevelClient client() {
		/*
        ClientConfiguration clientConfiguration 
            = ClientConfiguration.builder()
                .connectedTo("localhost:5601")
                .build();

        return RestClients.create(clientConfiguration).rest();
        
        */
		
		 final ClientConfiguration configuration = ClientConfiguration.localhost();
	        RestHighLevelClient client = RestClients.create(configuration).rest();
	        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.any())
	              .paths(PathSelectors.any())
	              .build()
	              .pathMapping("/");
	}

}
