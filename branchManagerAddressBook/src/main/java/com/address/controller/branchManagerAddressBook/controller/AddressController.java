package com.address.controller.branchManagerAddressBook.controller;

import com.address.controller.branchManagerAddressBook.entity.AddressEntity;
import com.address.controller.branchManagerAddressBook.enumclass.AddressType;
import com.address.controller.branchManagerAddressBook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/v1/address-book")
public class AddressController {

    @Autowired
    private ContactService service;
    @PostMapping("/address")
    public ResponseEntity<AddressEntity> saveContactToDB(@RequestBody AddressEntity address){
        service.saveContact(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{name}")
    public AddressEntity removeFromDB(@PathVariable (value = "name") String name){
        return service.removeContact(name);
    }

    @GetMapping("/find/{address}")
    public List<AddressEntity> findBasedOnContactType(@PathVariable (value = "address") AddressType address){
        return service.findContact(address);
    }


    /**
     * Dummy endpoints for fetching and deleting all records
     */
    @GetMapping("/getAll")
    public List<AddressEntity> getAllContact(){
        return service.getAll();
    }

    @DeleteMapping("/deleteAll")
    public String deleteContact(){
        return service.removeAll();
    }
}
