package com.lecloud.springbootlearn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by yili on 2017/7/18.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainQueryResultVO {
    List<DomainVO> domain;  //domain对应json中的一级属性名
}
