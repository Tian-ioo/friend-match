package com.briup.product_source.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IssueRecordMapper {
    List<Map<String,Integer>> countSales();
}