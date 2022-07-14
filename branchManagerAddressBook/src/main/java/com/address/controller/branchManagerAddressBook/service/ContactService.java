package com.address.controller.branchManagerAddressBook.service;
import com.address.controller.branchManagerAddressBook.dao.EntityRepository;
import com.address.controller.branchManagerAddressBook.entity.AddressEntity;
import com.address.controller.branchManagerAddressBook.enumclass.AddressType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private EntityRepository repository;
    public AddressEntity saveContact(AddressEntity entity){
        entity.setId(UUID.randomUUID().toString());
        entity.setAddress(entity.getAddress());

        return repository.save(entity);
    }
    public AddressEntity removeContact(String name){

        return repository.deleteByName(name);
    }
    public List<AddressEntity> findContact(AddressType addressType){
        List<AddressEntity> entities = new ArrayList<>();

        return repository.findByAddress(addressType);
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
