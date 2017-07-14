package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mehdimess404 on 7/14/2017.
 * Simple class in charge of abstracting the process of text file parsing using FileParser and BufferedReader.
 */
public class FileParser {

    private File file;
    private BufferedReader br;

    public FileParser(String filePath) throws FileNotFoundException {
        file = new File(filePath);
        br = new BufferedReader(new FileReader(file));
    }

    public List<String> getStrings() throws IOException {
        List<String> result = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null){
            result.add(line);
        }
        return result;
    }


}
