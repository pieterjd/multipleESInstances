package com.example.multipleESInstances.es8;

import com.example.multipleESInstances.model.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonES8Repository extends ElasticsearchRepository<Person, Long> {
    List<Person> findPersonByFullname(String fullname);


}
