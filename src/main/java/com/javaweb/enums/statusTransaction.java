package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum statusTransaction {
    CHUA_XU_LY("Chưa xử lý"),
    DANG_XU_LY("Đang xử lý"),
    DA_XU_LY("Đã xử lý");

    private String status;

    statusTransaction(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static Map<String,String> type(){
        Map<String,String> listType = new LinkedHashMap<>();
        for(statusTransaction item : statusTransaction.values()){
            listType.put(item.toString() , item.status);
        }
        return listType;
    }
}
