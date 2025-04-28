package io.sourceWare.Tests;

import io.sourceWare.program.client.model.file_handler.json.ContactPojo;
import io.sourceWare.program.client.model.file_handler.json.JsonHandler;

import java.util.List;

public class JsonTest {
    public static final String filePath = "Program/src/io/sourceWare/Tests/files/contacts.json";
    public static void main(String[] args) {
        System.out.println("Starting");
        List<ContactPojo> list = jsonReadTest();
        System.out.println(list.get(0) + " - " + (list.get(0).getId().equals("172.0.0.1:8080") ? "reading correct id ✅": "failed to read id ❌"));
        System.out.println(list.get(1) + " - " + (list.get(1).getAlias().equals("john doe") ? "reading correct alias ✅" : "failed to read alias ❌"));
        System.out.println("list size: " + list.size() + " - " + (list.size() == 2 ? "correct list size ✅" : "incorrect list size ❌"));

        System.out.println("json Read and Write test" + jsonReadAndWriteTest());

    }

   public static List<ContactPojo> jsonReadTest(){
      return JsonHandler.readContactJson(filePath);
   }

   public static void jsonWriteTest(){
        ContactPojo cp = new ContactPojo();
        cp.setId("172.0.0.1:8080");
        cp.setAlias("jany_doey");
        ContactPojo cp2 = new ContactPojo();
        cp2.setId("255.255.255:2001");
        cp2.setAlias("John doe");
        JsonHandler.writeContactJson(cp ,filePath);
        JsonHandler.writeContactJson(cp2,filePath);
   }

   public static List<ContactPojo> jsonReadAndWriteTest(){
        jsonWriteTest();
        return jsonReadTest();
   }
}
