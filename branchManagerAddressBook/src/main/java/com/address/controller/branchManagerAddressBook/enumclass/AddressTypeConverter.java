package com.address.controller.branchManagerAddressBook.enumclass;

import java.beans.PropertyEditorSupport;

public class AddressTypeConverter extends PropertyEditorSupport {
    public void setAsText(final String text) throws IllegalArgumentException{
        setValue(AddressType.fromType(text));
    }
}
