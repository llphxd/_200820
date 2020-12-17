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
public class HBase_API_03 {
        public static void main(String[] args) throws IOException {
            final Configuration cfg = HBaseConfiguration.create();
            cfg.set("hbase.zookeeper.quorum","hxd01,hxd02,hxd03");
            Connection connection = ConnectionFactory.createConnection(cfg);
            final Admin admin = connection.getAdmin();

            final TableName student = TableName.valueOf("atguigu:student");
            final boolean b = admin.tableExists(student);
            if (b){
                System.out.println("表已存在");
            }else{
                System.out.println("表不存在,create");
//                HTableDescriptor htd = new HTableDescriptor(student);
////                htd.addFamily(new HColumnDescriptor("info"));
////                admin.createTable(htd);
////                System.out.println("表创建成功");
                TableDescriptorBuilder builder = TableDescriptorBuilder.newBuilder(student);
                ColumnFamilyDescriptorBuilder info = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("info"));
                builder.setColumnFamily(info.build());
                admin.createTable(builder.build());
                System.out.println("表创建成功");

            }

            admin.close();
            connection.close();

        }
}
