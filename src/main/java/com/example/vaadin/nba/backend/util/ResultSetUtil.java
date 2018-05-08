package com.example.vaadin.nba.backend.util;

import com.example.vaadin.nba.backend.data.entity.stats.ResultSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSetUtil {

    public static List<Map<String, String>> getValueMapList(ResultSet rs) {
        List<Map<String, String>> valueMapList = new ArrayList<>();
        List<String> headerList = rs.getHeaders();
        List<List<String>> rowSetList = rs.getRowSet();
        for (List<String> valueList : rowSetList) {
            Map<String, String> dataSet = new HashMap<>();
            for (int i = 0; i < valueList.size(); i++) {
                dataSet.put(headerList.get(i), valueList.get(i));
            }
            valueMapList.add(dataSet);
        }
        return valueMapList;
    }
}
