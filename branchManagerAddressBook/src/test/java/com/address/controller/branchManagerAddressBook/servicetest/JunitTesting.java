package com.address.controller.branchManagerAddressBook.servicetest;

import com.address.controller.branchManagerAddressBook.dao.EntityRepository;
import com.address.controller.branchManagerAddressBook.entity.AddressEntity;
import com.address.controller.branchManagerAddressBook.enumclass.AddressType;
import com.address.controller.branchManagerAddressBook.service.ContactService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class JunitTesting {
    @Mock
    EntityRepository repository;
    @InjectMocks
    ContactService service;

    MockMvc mockMvc;

    List<AddressEntity> entities ;
    @Test
    @Order(1)
    public void test_saveContact(){
        List<AddressEntity> entities = new ArrayList<AddressEntity>();

        AddressEntity entity = new AddressEntity("1", "Himanshu", Arrays.asList("1","2"), AddressType.FRIENDS);
        when(repository.save(entity)).thenReturn(entity);

        assertThat(entity.getName()).isEqualTo("Himanshu");
    }

    @Test
    @Order(2)
    public void test_removeContact(){
        List<AddressEntity> entities = new ArrayList<AddressEntity>();
        AddressEntity entity = new AddressEntity("1", "Himanshu", Arrays.asList("1","2"), AddressType.FRIENDS);
        AddressEntity entity1 = new AddressEntity("1", "Vishal", Arrays.asList("1","2"), AddressType.FRIENDS);
        service.saveContact(entity);
        service.saveContact(entity1);

        when(repository.deleteByName("Himanshu")).thenReturn(entity);
        service.removeContact("Himanshu");
        assertEquals(false, repository.existsById("1"));

    }

    @Test
    public void test_findContact(){
        AddressEntity entity = new AddressEntity("1", "Himanshu", Arrays.asList("1","2"), AddressType.FRIENDS);
        AddressEntity entity1 = new AddressEntity("2", "Vishal", Arrays.asList("1","2"), AddressType.HOME);
        service.saveContact(entity);
        service.saveContact(entity1);
//        when(repository.findAll()).thenReturn(service.getAll());
        assertEquals(false,service.findContact(AddressType.FRIENDS).equals(entity));
    }
}
