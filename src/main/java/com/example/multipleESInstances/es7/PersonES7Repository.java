package com.example.multipleESInstances.es7;

import com.example.multipleESInstances.model.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonES7Repository extends ElasticsearchRepository<Person, Long> {
    List<Person> findPersonByFullname(String fullname);
}
