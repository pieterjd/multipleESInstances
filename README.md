# Sample Application connecting to 2 Elasticsearch Instances
This repository contains a Spring Boot application with Spring Data Elasticsearch.

This sample application connects to 2 ES instances running on localhost at ports 9200 and 9201 (eg. a docker container).

The keypoints are:
* Create a seperate package where you configure the connection to your ES instance and your interface extending ``ElasticSearchRepository``
* In your config class, reate a ``@Bean`` annotated method returning an ElasticSearchTemplate that uses a client connecting to your ES instance
* Also in your config class add the ``@EnableElasticsearchRepositories`` annotation, setting the ``basePackage`` attribute to the package of your configuration
and set the value of the ``elasticsearchTemplateRef`` attribute to the name of your ElasticsearchTemplate bean. 

For instance
````java
package com.example.multipleESInstances.es7;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
// basePackage points to the package where the config is stored
// elasticsearchTemplateRef is the name of the template bean to be used for the ES Repository
@EnableElasticsearchRepositories(basePackages = "com.example.multipleESInstances.es7",
elasticsearchTemplateRef = "es7Template")
public class ES7Config {

    @Bean(name = "es7Template")
    public ElasticsearchTemplate elasticsearchTemplate(){
        return new ElasticsearchTemplate(elasticClient());
    }


    public ElasticsearchClient elasticClient() {
        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "changeme"));
        RestClient client = RestClient.builder(
                        new HttpHost("localhost", 9200))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(
                            HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder
                                .setDefaultCredentialsProvider(credentialsProvider);
                    }
                }).build();
        ElasticsearchTransport transport = new RestClientTransport(client, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport) ;
    }
}
````