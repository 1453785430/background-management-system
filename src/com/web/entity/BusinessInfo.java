package com.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessInfo implements Serializable {
    private Integer id;
    private String shopName;
    private String tel;
    private String address;
    private Date createTime;
    private Integer status;
}
