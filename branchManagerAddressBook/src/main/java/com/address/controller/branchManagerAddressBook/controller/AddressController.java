package com.address.controller.branchManagerAddressBook.controller;

import com.address.controller.branchManagerAddressBook.entity.AddressEntity;
import com.address.controller.branchManagerAddressBook.enumclass.AddressType;
import com.address.controller.branchManagerAddressBook.exceptionhandler.UserAlreadyExist;
import com.address.controller.branchManagerAddressBook.exceptionhandler.UserNameNotFoundException;
import com.address.controller.branchManagerAddressBook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/v1/address-book")
public class AddressController {
    //Constructor Injection is used
    private ContactService service;
    @Autowired
    public AddressController(ContactService service) {
        this.service = service;
    }

    @PostMapping("/saving-address")
    public ResponseEntity<AddressEntity> saveNewContact(@RequestBody AddressEntity address) throws UserAlreadyExist {
        service.saveContact(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{name}")
    public AddressEntity removingDataAsPerName(@PathVariable (value = "name") String name) throws UserNameNotFoundException {
        return service.removeContact(name);
    }

    @GetMapping("/find/{address}")
    public List<AddressEntity> findBasedOnContactType(@PathVariable (value = "address") AddressType address){
        return service.findContact(address);
    }

    @GetMapping("/unique-contactList")
    public List<String > uniqueListOfContact(){
        return service.findUnique();
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
