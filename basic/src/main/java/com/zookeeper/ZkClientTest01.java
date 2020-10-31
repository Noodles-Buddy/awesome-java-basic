package com.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * zookeeper客户端
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-07-11 09:15
 */
public class ZkClientTest01 {

    private String ZK_ADDRESS = "";
    private int SESSION_TIMEOUT = 2000;
    private ZooKeeper zooKeeper;

    /**
     * 建立zookeeper连接
     *
     * @throws Exception
     */
    @Before
    public void init() throws Exception{
        zooKeeper = new ZooKeeper(ZK_ADDRESS, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                List<String> children;
                try {
                    children = zooKeeper.getChildren("/", true);
                    children.forEach(System.out::println);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 创建节点
     *
     * acl是什么？
     */
    @Test
    public void create() throws Exception{
        zooKeeper.create("/vincent", "vincent add oil".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
    }

    /**
     * 获取节点、监控数据变化
     * 添加watch监听，节点变化时，触发init() 中的process()
     */
    @Test
    public void getAndWatch() throws Exception{
        List<String> children = zooKeeper.getChildren("/", true);
    }

    /**
     * 判断节点是否存在
     *
     * @throws Exception
     */
    @Test
    public void nodeExist() throws Exception {
        Stat stat = zooKeeper.exists("/", false);
        System.out.println(stat);
    }
}
