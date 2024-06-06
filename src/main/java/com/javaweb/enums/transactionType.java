package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public enum transactionType {
    CSKH("Chăm sóc khách hàng"),
    DDX("Dẫn đi xem");
    private String name;

    transactionType(String name) {
        this.name = name;
    }

    public static Map<String,String> type(){
        Map<String,String> listType = new LinkedHashMap<>();
        for(transactionType item : transactionType.values()){
            listType.put(item.toString() , item.name);
        }
        return listType;
    }
}
