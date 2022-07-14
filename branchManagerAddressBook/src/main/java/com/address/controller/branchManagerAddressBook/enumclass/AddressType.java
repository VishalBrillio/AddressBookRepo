package com.address.controller.branchManagerAddressBook.enumclass;

public enum AddressType {
    SOCIAL("SOCIAL"), FRIENDS("FRIENDS"), OFFICE("OFFICE"), HOME("HOME");

    private String type;
    AddressType(String type){
        this.type = type;
    }
   public static AddressType fromType(String type){
        for(AddressType t : values()){
            if(t.type.equalsIgnoreCase(type)){
                return t;
            }
        }
        throw new IllegalArgumentException("Unknown Enum Type");
   }
}
