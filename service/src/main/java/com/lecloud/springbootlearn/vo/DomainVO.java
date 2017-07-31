package com.lecloud.springbootlearn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yili on 2017/7/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainVO {
    private String domain;     //domain,serviceType对应json中的二级属性名
    private String serviceType;
}
