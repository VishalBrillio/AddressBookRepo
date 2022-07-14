package com.address.controller.branchManagerAddressBook.dao;
import com.address.controller.branchManagerAddressBook.entity.AddressEntity;
import com.address.controller.branchManagerAddressBook.enumclass.AddressType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EntityRepository extends MongoRepository<AddressEntity, String> {
//    @Query("{name : '?0'}")
    AddressEntity deleteByName(String name);
    @Query(value = "{address : ?0}", fields = "{name:1, mobileNumber:1}")
    List<AddressEntity> findByAddress(AddressType addressType);
}
