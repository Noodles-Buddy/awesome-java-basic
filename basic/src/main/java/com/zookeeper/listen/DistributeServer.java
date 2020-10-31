package com.zookeeper.listen;

import org.apache.zookeeper.*;

import java.util.List;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-07-12 09:50
 */
public class DistributeServer {

    private String ZK_ADDRESS = "127.0.0.1:2181";
    private int SESSION_TIMEOUT = 2000;
    private ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception{
        DistributeServer server = new DistributeServer();

        //1.连接 zk 集群
        server.getConnect();

        //2.注册节点
        server.regist("");

        //3.业务处理
        Thread.sleep(100000000);
    }


    private void regist(String host) throws Exception{
        zooKeeper.create("/servers1", "abc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(host + " is online!");
    }


    private void getConnect() throws Exception{
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



}
