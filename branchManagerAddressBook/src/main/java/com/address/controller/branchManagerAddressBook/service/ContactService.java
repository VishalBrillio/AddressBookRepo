package com.address.controller.branchManagerAddressBook.service;
import com.address.controller.branchManagerAddressBook.dao.EntityRepository;
import com.address.controller.branchManagerAddressBook.entity.AddressEntity;
import com.address.controller.branchManagerAddressBook.enumclass.AddressType;
import com.address.controller.branchManagerAddressBook.exceptionhandler.UserAlreadyExist;
import com.address.controller.branchManagerAddressBook.exceptionhandler.UserNameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class ContactService {

    //Constructor Injection is used
    private EntityRepository repository;
    @Autowired
    public ContactService(EntityRepository repository) {
        this.repository = repository;
    }

    public AddressEntity saveContact(AddressEntity entity) throws UserAlreadyExist {

        AddressEntity validEntity;
        validEntity = repository.findByName(entity.getName());
        if(validEntity != null && entity.getAddress().equals(validEntity.getAddress()) ){
            throw new UserAlreadyExist("User is Already exist");
        }
        else {
            entity.setId(UUID.randomUUID().toString());
            return repository.save(entity);
        }

    }
    public AddressEntity removeContact(String name) throws UserNameNotFoundException {
        AddressEntity entity;
        entity = repository.findByName(name);
        if(entity!=null){
            return repository.deleteByName(entity.getName());
        }
        else {
            throw new UserNameNotFoundException("User doesn't exist");
        }

    }
    public List<AddressEntity> findContact(AddressType addressType) throws MethodArgumentTypeMismatchException {
        List<AddressEntity> entities = new ArrayList<>();
        entities = repository.findAll();
        return repository.findByAddress(addressType);
    }

    public List<String> findUnique(){
        List<AddressEntity> firstList = new ArrayList<>();
        List<String> secondList = new ArrayList<>();
        firstList = repository.findAll();

        /**
         * [e1, e2, e3, e4] where e has id, name, listOfMobileNumber, address
         *
         * secondList has name
         */

        for(AddressEntity entity : firstList){
            if(!(secondList.contains(entity.getName()))){
                secondList.add(entity.getName());
            }
        }
        return secondList;
    }


    /**
     * Two Dummy methods for checking all records and deleting all records
     */
    public List<AddressEntity> getAll(){
        return repository.findAll();
    }

    public String removeAll(){
        repository.deleteAll();
        return "All records deleted";
    }


}
