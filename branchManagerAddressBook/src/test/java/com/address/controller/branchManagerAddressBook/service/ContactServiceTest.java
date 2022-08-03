package com.address.controller.branchManagerAddressBook.service;

import com.address.controller.branchManagerAddressBook.dao.EntityRepository;
import com.address.controller.branchManagerAddressBook.entity.AddressEntity;
import com.address.controller.branchManagerAddressBook.enumclass.AddressType;
import com.address.controller.branchManagerAddressBook.exceptionhandler.UserAlreadyExist;
import com.address.controller.branchManagerAddressBook.exceptionhandler.UserNameNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ContactServiceTest {
    @Mock
    EntityRepository repository;
    @InjectMocks
    ContactService service;

    MockMvc mockMvc;

    @Test
    @Order(1)
    public void test_saveContact() throws UserAlreadyExist {
        List<AddressEntity> entities = new ArrayList<AddressEntity>();

        AddressEntity entity = new AddressEntity("1", "Himanshu", Arrays.asList("1","2"), AddressType.FRIENDS);
        when(repository.save(entity)).thenReturn(entity);
        assertEquals(service.saveContact(entity), entity);
    }

    @Test
    @Order(2)
    public void test_removeContact() throws UserAlreadyExist, UserNameNotFoundException, Exception {
        AddressEntity entity = new AddressEntity("1", "Himanshu", Arrays.asList("1","2"), AddressType.FRIENDS);
        when(repository.save(entity)).thenReturn(entity);
        when(repository.findByName(entity.getName())).thenReturn(entity);
        this.service.removeContact(entity.getName());
        verify(repository, times(1)).delete(entity);
    }

    @Test
    public void test_findContact() throws UserAlreadyExist {
        AddressEntity entity = new AddressEntity("1", "Himanshu", Arrays.asList("1","2"), AddressType.FRIENDS);
        AddressEntity entity1 = new AddressEntity("2", "Vishal", Arrays.asList("1","2"), AddressType.HOME);
        service.saveContact(entity);
        service.saveContact(entity1);
//        when(repository.findAll()).thenReturn(service.getAll());
        assertEquals(false,service.findContact(AddressType.FRIENDS).equals(entity));
    }
}