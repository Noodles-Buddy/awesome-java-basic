package com.basic.generic.generatorClass.partice;

import java.util.*;

/**
 * @Author: zhenhua.zhang
 * @Date: 2020-10-31 09:33
 */
public class Dao<T> {

    private Map<String, T> map = new HashMap<>(16);

    public void insert(Map<String, T> param){
        map.putAll(param);
    }

    public T get(String param) {
        return map.get(param);
    }

    public void update(String key, T value) {
        if (null != map.get(key)) {
            map.put(key, value);
        }
    }

    public List<T> getAllElement() {
        List<T> result = new ArrayList<>();
        Iterator<Map.Entry<String, T>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, T> entry = (Map.Entry)it.next();
            result.add(entry.getValue());
        }
        return result;
    }

    public void delete(String key) {
        if (null != map.get(key)) {
            map.remove(key);
        }
    }
}
