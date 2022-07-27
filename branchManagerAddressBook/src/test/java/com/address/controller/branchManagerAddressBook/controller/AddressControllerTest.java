package com.address.controller.branchManagerAddressBook.controller;
import com.address.controller.branchManagerAddressBook.dao.EntityRepository;
import com.address.controller.branchManagerAddressBook.entity.AddressEntity;
import com.address.controller.branchManagerAddressBook.enumclass.AddressType;
import com.address.controller.branchManagerAddressBook.exceptionhandler.UserAlreadyExist;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;


@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EntityRepository repo;
    private final JsonMapper mapper = JsonMapper.builder().build();
    @BeforeEach
    public void setUp(){
        this.repo.deleteAll();;
    }

    @Test
    void saveNewContact_Test() throws Exception {
        AddressEntity entity = new AddressEntity("1", "Himanshu", Arrays.asList("1","2"), AddressType.FRIENDS);
        final String json = mapper.writeValueAsString(entity);
        final String uri = "/app/v1/address-book/saving-address";
        this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(entity.getName()))
                .andExpect(jsonPath("$.id").exists());
    }
    @Test
    void removingDataAsPerName_Test() throws Exception {
        AddressEntity entity = new AddressEntity("1", "Himanshu", Arrays.asList("1","2"), AddressType.FRIENDS);
        final AddressEntity savedEntity = this.repo.save(entity);
        final String json = mapper.writeValueAsString(entity);
        final String uri = "/app/v1/address-book/delete/Himanshu";
        this.mockMvc.perform(delete(uri))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    void findBasedOnContactType_Test() throws Exception {
        AddressEntity entity = new AddressEntity("1", "Himanshu", Arrays.asList("1","2"), AddressType.FRIENDS);
        final AddressEntity savedEntity = this.repo.save(entity);
        final String json = mapper.writeValueAsString(entity);

        final String uri = "/app/v1/address-book/find/" + entity.getAddress();
        this.mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void uniqueListOfContact_Test() throws Exception {
        AddressEntity entity = new AddressEntity("1", "Himanshu", Arrays.asList("1","2"), AddressType.FRIENDS);
        final AddressEntity savedEntity = this.repo.save(entity);
        final String uri = "/app/v1/address-book/unique-contactList";
        this.mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
