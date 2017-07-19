package com.techprimers.elastic.load;

import com.techprimers.elastic.model.Users;
import com.techprimers.elastic.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Loaders {

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    UsersRepository usersRepository;

    @PostConstruct
    @Transactional
    public void loadAll(){

        operations.putMapping(Users.class);
        System.out.println("Loading Data");
        usersRepository.save(getData());
        System.out.printf("Loading Completed");

    }

    private List<Users> getData() {
        List<Users> userses = new ArrayList<>();
        userses.add(new Users("Ajay",123L, "Accounting", 12000L));
        userses.add(new Users("Jaga",1234L, "Finance", 22000L));
        userses.add(new Users("Thiru",1235L, "Accounting", 12000L));
        userses.add(new Users("AjayS Oliveros",1236L, "Accounting", 12000L));
        userses.add(new Users("AjaZS Oliveros",1239L, "Accounting", 12000L));
        userses.add(new Users("BaJaga nim",1237L, "OFFICe", 22000L));
        userses.add(new Users("ThiruT t",1238L, "Accounting", 12000L));
        userses.add(new Users("PASTA - carbonara",1239L, "Kitchen", 12000L));
        userses.add(new Users("PASTA - spaghetti",1240L, "Kitchen", 12000L));
        userses.add(new Users("PASTA - lenguini",1241L, "Kitchen", 120030L));
        userses.add(new Users("spaghetti con karne",1242L, "Kitchen", 120040L));
        userses.add(new Users("PASTA - spaghetti - jelly",1243L, "Finance", 120700L));
        userses.add(new Users("jam KAjaZS",1244L, "Finance", 120700L));
        userses.add(new Users("KAjaZS",1245L, "Finance", 123000L));
        userses.add(new Users("naKAjaZS",1246L, "Finance", 123000L));
        userses.add(new Users("dafKAjaZS",1247L, "Finance", 123000L));
        userses.add(new Users("Accounting dafKAjaZS",1247L, "Finance", 123000L));
        return userses;
    }
}
