package com.atguigu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author hxd  Email：llphxdgg@gmail.com
 * @description
 * @create 2020-12-09 20:35
 */
public class HBase_API_06 {
    public static void main(String[] args) throws IOException {
        final Configuration cfg = HBaseConfiguration.create();
        cfg.set("hbase.zookeeper.quorum", "hxd01,hxd02,hxd03");
        Connection connection = ConnectionFactory.createConnection(cfg);
        final TableName student = TableName.valueOf("atguigu:student");

        Table table = connection.getTable(student);
        Get get = new Get(Bytes.toBytes("1001"));
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)));
            System.out.println(Bytes.toString(CellUtil.cloneQualifier(cell)));
            System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
        }

        System.out.println("------------------------------------------");

        ResultScanner scanner = table.getScanner(new Scan());
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()) {
            Result next = iterator.next();
            Cell[] cells1 = next.rawCells();
            for (Cell cell : cells1) {
                System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)));
                System.out.println(Bytes.toString(CellUtil.cloneQualifier(cell)));
                System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }

        table.close();
        connection.close();

    }
}
