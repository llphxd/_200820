package com.atguigu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author hxd  Email：llphxdgg@gmail.com
 * @description
 * @create 2020-12-09 20:35
 */
public class HBase_API_05 {
    public static void main(String[] args) throws IOException {
        final Configuration cfg = HBaseConfiguration.create();
        cfg.set("hbase.zookeeper.quorum", "hxd01,hxd02,hxd03");
        Connection connection = ConnectionFactory.createConnection(cfg);
        final TableName student = TableName.valueOf("atguigu:student");

        Table table = connection.getTable(student);
        Put put = new Put(Bytes.toBytes("1002"));
        put.addColumn(
                Bytes.toBytes("info"),
                Bytes.toBytes("name"),
                Bytes.toBytes("zhangsan"));
        table.put(put);
        System.out.println("新增数据成功");


        table.close();
        connection.close();

    }
}
