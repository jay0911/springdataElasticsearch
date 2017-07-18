package com.techprimers.elastic.repository;

import com.techprimers.elastic.model.Users;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UsersRepository extends ElasticsearchRepository<Users, Long> {
	
	@Query("{\"bool\" : {\"should\" : [{\"match\" : {\"name\" : \"?0\"}}, {\"match\" : {\"teamName\" : \"?0\"}}]}}")
    List<Users> findByName(String text);

    List<Users> findBySalary(Long salary);
}
