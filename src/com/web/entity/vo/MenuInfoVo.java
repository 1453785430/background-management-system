package com.web.entity.vo;

import com.web.entity.MenuInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuInfoVo extends MenuInfo implements Serializable {
    private List<MenuInfo> list;
}
