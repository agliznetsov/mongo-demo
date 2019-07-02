package com.example.mongodemo.service;

import com.example.mongodemo.dao.ProductionRepository;
import com.example.mongodemo.dao.UserRepository;
import com.example.mongodemo.model.Production;
import com.example.mongodemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MyService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductionRepository productionRepository;

    @Transactional
    public void trySaveUserProduction(String name, boolean withError) {
        User user = new User();
        user.setName(name);
        userRepository.save(user);

        Production production = new Production();
        production.setName(name);
        productionRepository.save(production);

        if (withError) {
            throw new IllegalStateException("runtime error");
        }
    }
}
