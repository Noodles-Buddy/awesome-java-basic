package com.zookeeper.listen;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-07-12 10:08
 */
public class DistributeClient {

    private String ZK_ADDRESS = "127.0.0.1:2181";
    private int SESSION_TIMEOUT = 2000;
    private ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception{
        DistributeClient client = new DistributeClient();

        //1 获取集群
        client.getConnection();

        //2 注册监听
        client.getChildren();

        //3 业务处理
    }

    /**
     * 获取服务器列表，场景：1.main()  2.process() 主动监听触发
     *
     * 为什么每次只监听1次却可以一直监听，每次getChildren() 都触发重新 zk.getChildren并设置watch为true.
     *
     * @throws Exception
     */
    private void getChildren() throws Exception{
        List<String> children = zooKeeper.getChildren("/servers", true);
        ArrayList<String> hosts = new ArrayList<>();

        hosts.stream().forEach(host -> {
            try {
                byte[] data = zooKeeper.getData("/servers/" + host, false, null);
                hosts.add(data.toString());
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(hosts);
    }

    /**
     * 连接到zk集群，收到监听后，触发 getChildren()
     *
     * @throws Exception
     */
    private void getConnection() throws Exception{
        zooKeeper = new ZooKeeper(ZK_ADDRESS, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    getChildren();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
