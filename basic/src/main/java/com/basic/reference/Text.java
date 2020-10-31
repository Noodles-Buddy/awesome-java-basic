package com.basic.reference;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-06-24 18:33
 */
public class Text {

    private static void test(CmsAtUser c) {
        c.setUsername("活着");
    }

    private static CmsAtUser test2(CmsAtUser c) {
        c = null;
        System.out.println("回收对象");
        return c;
    }

    public static void main(String[] args) {
        CmsAtUser user = new CmsAtUser();
        user.setUsername("第一条命");

        System.out.println(user.getUsername());
        user = test2(user);
        System.out.println("user=" + user);
    }

}

class CmsAtUser {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}