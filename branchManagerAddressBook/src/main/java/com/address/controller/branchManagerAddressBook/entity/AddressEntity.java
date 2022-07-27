package com.address.controller.branchManagerAddressBook.entity;


import com.address.controller.branchManagerAddressBook.enumclass.AddressType;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Document("contactbook")
@AllArgsConstructor
//@Getter
@Setter
@NoArgsConstructor
public class AddressEntity {
    @Id
    private String id;
    @NotNull
    private String name;
    private List<String> mobileNumber;
    private AddressType address;

}
