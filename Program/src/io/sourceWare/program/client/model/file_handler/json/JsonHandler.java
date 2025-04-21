package io.sourceWare.program.client.model.file_handler.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.*;
import java.io.File;
import java.io.IOException;

public class JsonHandler {
    // handles JsonFiles
    public ContactPojo[] readContactJson(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            // what is going on here?
            ContactPojo[] contacts = mapper.readValue(new File("contacts.json"), ContactPojo[].class);

            for (ContactPojo c : contacts ){
                // here is where JSON data can be sent to other files
                System.out.println("id" + c.id);
                System.out.println("Alias" + c.alias);
            }

            return contacts;

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
