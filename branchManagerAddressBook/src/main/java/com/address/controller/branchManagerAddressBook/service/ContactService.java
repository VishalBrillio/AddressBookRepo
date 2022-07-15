package com.address.controller.branchManagerAddressBook.service;
import com.address.controller.branchManagerAddressBook.dao.EntityRepository;
import com.address.controller.branchManagerAddressBook.entity.AddressEntity;
import com.address.controller.branchManagerAddressBook.enumclass.AddressType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class ContactService {

    @Autowired
    private EntityRepository repository;
    public AddressEntity saveContact(AddressEntity entity){
        List<AddressEntity> contact = new ArrayList<>();
        contact = repository.findAll();
        for(AddressEntity address : contact){
            if(address.getName().equals(entity.getName()) && address.getAddress().equals(entity.getAddress())){
                entity.setId("Already Saved in DB");
                return entity;
            }
        }
        entity.setId(UUID.randomUUID().toString());
        return repository.save(entity);
    }
    public AddressEntity removeContact(String name) throws NullPointerException{
        List<AddressEntity> contact = new ArrayList<>();
        contact = repository.findAll();

        for(AddressEntity address : contact){
            if(address.getName().equals(name)){
                return repository.deleteByName(name);
            }
        }
        return null;
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

    public void findUniqueContact(){
        List<AddressEntity> firstList = new ArrayList<>();
        List<String> secondList = new ArrayList<>();
        firstList = repository.findAll();

        for(AddressEntity entity : firstList){
            if(!(secondList.contains(entity.getName()))){
                System.out.println(entity.getId() + entity.getName() + entity.getMobileNumber() + entity.getAddress());
            }
        }

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
