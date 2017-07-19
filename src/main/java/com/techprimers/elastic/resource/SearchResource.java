package com.techprimers.elastic.resource;

import com.techprimers.elastic.model.Users;
import com.techprimers.elastic.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/search")
public class SearchResource {

    @Autowired
    UsersRepository usersRepository;

    /**
     * get all data where users must have name = {parameter}
     * @param name
     * @return
     */
    @GetMapping(value = "/name/{text}")
    public List<Users> searchName(@PathVariable final String text) {
        return usersRepository.findByName(text);
    }

    /**
     * searching id using path variable eg: localhost:80/salary/12000
     * @param salary
     * @return list of users where salary is parameter
     */
    @GetMapping(value = "/salary/{salary}")
    public List<Users> searchSalary(@PathVariable final Long salary) {
        return usersRepository.findBySalary(salary);
    }
    
    /**
     * searching id using request param eg: localhost:80/salary2?id=12000
     * @param salary
     * @return list of users where salary is parameter
     */
    @GetMapping(value = "/salary2")
    public List<Users> searchSalaryReqParam(@RequestParam("id") final String salary) {
        return usersRepository.findBySalary(Long.parseLong(salary));
    }


    /**
     * search all fields in the elastic search database
     * @return all data
     */
    @GetMapping(value = "/all")
    public List<Users> searchAll() {
        List<Users> usersList = new ArrayList<>();
        Iterable<Users> userses = usersRepository.findAll();
        userses.forEach(usersList::add);
        return usersList;
    }
    
    /**
     * search name or team name in the data
     * @return all data that has name or team name in the parameter
     */
    @GetMapping(value = "/nameorteam")
    public List<Users> searchNameOrTeamName(@RequestParam("search") final String search) {
        List<Users> usersList = new ArrayList<>();
        Iterable<Users> userses = usersRepository.findByNameOrTeamName(search);
        userses.forEach(usersList::add);
        return usersList;
    }
    
    /**
     * search name containing 
     * @return all data that has name or team name in the parameter
     */
    @GetMapping(value = "/namecontaining")
    public List<Users> searchNameContaining(@RequestParam("search") final String search) {
        List<Users> usersList = new ArrayList<>();
        Iterable<Users> userses = usersRepository.findByNameContaining(search);
        userses.forEach(usersList::add);
        return usersList;
    }
    
    /**
     * search name AND team name in the data with scoring. satisfy both parameters will have the top match
     * @return all data that has name AND team name in the parameter
     */
    @GetMapping(value = "/searchscoring")
    public List<Users> searchNameOrTeamName(@RequestParam("search1") final String search1,@RequestParam("search2") final String search2) {
        List<Users> usersList = new ArrayList<>();
        Iterable<Users> userses = usersRepository.mustHaveNameShouldHaveName(search1, search2);
        userses.forEach(usersList::add);
        return usersList;
    }
    
    /**
     * search will have a wildcard whether the search word at any position.
     * the technique here is to add * at first and beginning of the search word eg: *search*
     * and use query_string analyze_wildcard=true
     * @return all data that has search word in any position of name column
     */
    @GetMapping(value = "/namewildcard")
    public List<Users> searchNameWildCard(@RequestParam("search") final String search) {
        List<Users> usersList = new ArrayList<>();
        Iterable<Users> userses = usersRepository.findByNameContaining(makeItWildCard(search));
        userses.forEach(usersList::add);
        return usersList;
    }
    
    /**
     * search will have a wildcard whether the search word at any position.
     * the technique here is to add * at first and beginning of the search word eg: *search*
     * and use query_string analyze_wildcard=true
     * 
     * (note) tried 2 parameters and 2 query_string in this method
     * @return all data that has search word in any position of name column
     */
    @GetMapping(value = "/nameteamwildcard")
    public List<Users> searchNameWildCard(@RequestParam("search1") final String search1,@RequestParam("search2") final String search2) {
        List<Users> usersList = new ArrayList<>();
        Iterable<Users> userses = usersRepository.findByNameContaining(makeItWildCard(search1), makeItWildCard(search2));
        userses.forEach(usersList::add);
        return usersList;
    }
    
    /**
     * util that makes a string wildcard search string
     * @param param
     * @return *param*
     */
    private String makeItWildCard(String param){
    	StringBuilder sb = new StringBuilder();
    	sb.append("*");
    	sb.append(param);
    	sb.append("*");
    	return sb.toString();
    }
    


}
