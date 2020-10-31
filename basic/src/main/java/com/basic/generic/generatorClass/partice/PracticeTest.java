package com.basic.generic.generatorClass.partice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhenhua.zhang
 * @Date: 2020-10-31 09:46
 */
public class PracticeTest {

    public static void main(String[] args) {
        Dao<User> userDao = new Dao<>();
        Map<String, User> map = new HashMap<>();
        map.put("1001", new User("1001", 20, "Vincent"));
        map.put("1002", new User("1002", 21, "Celine"));
        map.put("1003", new User("1003", 22, "Senifer"));

        userDao.insert(map);

        List<User> userList = userDao.getAllElement();
        System.out.println(userList);

        User user = userDao.get("1001");
        System.out.println(user);
        userDao.update("1001", new User("1001", 25, "Vincent"));

        user = userDao.get("1001");
        System.out.println(user);
    }

}
