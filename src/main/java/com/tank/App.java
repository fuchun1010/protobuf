package com.tank;

import com.google.protobuf.InvalidProtocolBufferException;
import com.googlecode.protobuf.format.JsonFormat;
import com.tank.proto.RecordMsg;

/**
 * @author fuchun
 */
public class App {
  public static void main(String[] args) {

    RecordMsg.Record.Schema schema = RecordMsg.Record.Schema.newBuilder()
        .setIndex(0)
        .setName("name")
        .setPk(false)
        .setFieldType(RecordMsg.Record.DataType.TEXT)
        .build();

    RecordMsg.Record.Schema schema2 = RecordMsg.Record.Schema.newBuilder()
        .setIndex(1)
        .setName("gender")
        .setPk(false)
        .setFieldType(RecordMsg.Record.DataType.INT)
        .build();

    RecordMsg.Record.Schema schema3 = RecordMsg.Record.Schema.newBuilder()
        .setIndex(2)
        .setName("id")
        .setPk(true)
        .setFieldType(RecordMsg.Record.DataType.INT)
        .build();

    RecordMsg.Record record = RecordMsg.Record.newBuilder()
        .setDatabase("demo2")
        .setTable("person")
        .setOp(RecordMsg.Record.Operator.DELETE)
        .addSchema(schema)
        .addSchema(schema2)
        .addSchema(schema3)
        .build();

    System.out.println("args = [" + record.toByteArray().length + "]");

    System.out.println("args = [" + JsonFormat.printToString(record).getBytes().length + "]");

    try {
      RecordMsg.Record tmpRcd = RecordMsg.Record.parseFrom(record.toByteArray());
      System.out.println("temp record = [" + tmpRcd.getDatabase() + "]");
    } catch (InvalidProtocolBufferException e) {
      e.printStackTrace();
    }
  }
}
