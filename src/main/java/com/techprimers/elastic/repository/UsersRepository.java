package com.techprimers.elastic.repository;

import com.techprimers.elastic.model.Users;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UsersRepository extends ElasticsearchRepository<Users, Long> {
	
	
    List<Users> findByName(String text);

    List<Users> findBySalary(Long salary);
    
	@Query("{\"bool\" : {\"should\" : [{\"match\" : {\"name\" : \"?0\"}}, {\"match\" : {\"teamName\" : \"?0\"}}]}}")
    List<Users> findByNameOrTeamName(String text);
	
	@Query("{\"bool\" : {\"must\" : ["
			+ "{\"match\" : {\"name\" : \"?0\"}}"
			+ "],\"should\" : [{\"match\" : {\"name\" : \"?1\"}}]"
			+ "}}")
    List<Users> mustHaveNameShouldHaveName(String param1,String param2);
	
	@Query("{\"bool\":{\"must\":["
			+ "{\"query_string\":{\"query\":\"?0\",\"fields\":[\"name\"],\"analyze_wildcard\":true}}"
			+ "]}}")
	List<Users> findByNameContaining(String text);
	
	@Query("{\"bool\":{\"should\":["
			+ "{\"query_string\":{\"query\":\"?0\",\"fields\":[\"name\"],\"analyze_wildcard\":true}},"
			+ "{\"query_string\":{\"query\":\"?1\",\"fields\":[\"teamName\"],\"analyze_wildcard\":true}}"
			+ "]}}")
	List<Users> findByNameContaining(String text1,String text2);
	
}
