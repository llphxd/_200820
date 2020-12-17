package com.atguigu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author hxd  Email：llphxdgg@gmail.com
 * @description
 * @create 2020-12-09 19:40
 */
public class HBase_API_01 {
    public static void main(String[] args) throws IOException {
        final Configuration cfg = HBaseConfiguration.create();
        cfg.set("hbase.zookeeper.quorum","hxd01,hxd02,hxd03");
        Connection connection = ConnectionFactory.createConnection(cfg);
        final Admin admin = connection.getAdmin();

        NamespaceDescriptor build = NamespaceDescriptor.create("atguigu").build();
        admin.createNamespace(build);
        System.out.println("命名空间创建成功");

        admin.close();
        connection.close();
    }
}
