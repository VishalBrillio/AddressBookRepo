package com.address.controller.branchManagerAddressBook.service;

import com.address.controller.branchManagerAddressBook.dao.EntityRepository;
import com.address.controller.branchManagerAddressBook.entity.AddressEntity;
import com.address.controller.branchManagerAddressBook.enumclass.AddressType;
import com.address.controller.branchManagerAddressBook.exceptionhandler.UserAlreadyExist;
import com.address.controller.branchManagerAddressBook.exceptionhandler.UserNameNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class AddressServiceTest {
    @Mock
    private EntityRepository repository;
    @InjectMocks
    private ContactService service;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void test_saveContact() throws UserAlreadyExist {
        AddressEntity entity = new AddressEntity("1", "Himanshu", Arrays.asList("1","2"), AddressType.FRIENDS);
        when(repository.save(entity)).thenReturn(entity);
        AddressEntity result = service.saveContact(entity);
        assertEquals(entity, result);
        verify(repository).save(entity);
    }

    @Test
    @Disabled
    public void test_saveContact_should_throw_anException() throws UserAlreadyExist{
        assertThrows(NullPointerException.class, ()-> {
            service.saveContact(null);
        });
    }

    @Test
    public void removeContact_should_delete_by_name() throws UserNameNotFoundException {
        AddressEntity entity = new AddressEntity("1", "Himanshu", Arrays.asList("1","2"), AddressType.FRIENDS);
        when(repository.findByName(entity.getName())).thenReturn(entity);
        when(repository.deleteByName(entity.getName())).thenReturn(entity);
        AddressEntity entity1 = service.removeContact(entity.getName());
        assertEquals(entity, entity1);
        verify(repository).findByName("Himanshu");
        verify(repository).deleteByName("Himanshu");
    }

    @Test
    public void remove_contact_should_throw_an_exception(){
        assertThrows(UserNameNotFoundException.class, ()-> {
            service.removeContact(null);
        });
    }

    @Test
    public void find_unique_contact_list(){
        List<AddressEntity> listA = new ArrayList<>();
        List<String> listB = new ArrayList<>();
        when(repository.findAll()).thenReturn(listA);
        listB = service.findUnique();
        assertTrue(listA.size() >= listB.size());
        verify(repository).findAll();
    }

}
