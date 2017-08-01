package com.lecloud.springbootlearn.service;

import com.lecloud.springbootlearn.vo.DomainVO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// HashMap 非线程安全
// HashTable 线程安全
// ConcurrentHashMap 线程安全
//HashMap的键和值都允许有null值存在，而HashTable则不行。
//因为线程安全的问题，HashMap效率比HashTable的要高。
//三者的关联：http://www.cnblogs.com/wang-meng/p/5808006.html

public class CacheLocal {
    Map<String, DomainVO> domainCache = new ConcurrentHashMap<>();

    public DomainVO getDomain(String userid) {
        //如果userid对应的domain存在则直接返回，否则计算获取
        return domainCache.computeIfAbsent(userid, uid -> getUserDomain(uid));
    }

    DomainVO getUserDomain(String userid) {
        DomainVO d = null;
        return d;
    }

}
