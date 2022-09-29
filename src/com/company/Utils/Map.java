package com.company.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;


public class Map {
    public String jbdcURL;
    public void Mapping() throws IOException {
        String filePath = "test.txt";
        HashMap<String, String> map = new HashMap<String, String>();

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while ((line = reader.readLine()) != null)
        {
            String[] parts = line.split("=", 2);
            if (parts.length >= 2)
            {
                String key = parts[0];
                String value = parts[1];
                map.put(key, value);
            } else {
                System.out.println("ignoring line: " + line);
            }
        }

        for (String key : map.keySet())
        {   this.jbdcURL =  map.get(key);
            //   System.out.println(key + "=" + map.get(key));
                 System.out.println(jbdcURL);

        }
        reader.close();
    }
}
