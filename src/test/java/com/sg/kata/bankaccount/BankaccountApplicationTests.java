package com.sg.kata.bankaccount;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)

@SpringBootTest
class BankAccountApplicationTests {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void contextLoads() {
        // Vérifie que le contexte Spring se charge correctement et que le bean ModelMapper est bien injecté
        assertThat(modelMapper).isNotNull();
    }

}
