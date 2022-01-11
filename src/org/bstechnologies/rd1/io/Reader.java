package org.bstechnologies.rd1.io;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private JSONObject json;
    public Reader(String path){
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(path);
            Object obj = parser.parse(reader);
            json = (JSONObject)obj;

        } catch (FileNotFoundException e) {
            System.out.println("Alg File Not Found");
        }catch (ParseException e) {
            System.out.println("Alg File didn't parsed correctly");
        } catch (IOException e) {
            System.out.println("Exception caught in IO");
        }
    }
    public String getAlg() throws EncException {
        String str = (String) json.get("alg");
        if(str == null)
        {
            throw new EncException("alg not Found");
        }

        return str;
    }
    public String enc(String src,String in) throws EncException {
        String src$n = (String) json.get(src);
        if(src$n == null)
        {
            throw new EncException("Invalid Source Code ");
        }
        if(in.equals(" "))
        {
            return "0110";
        }
        JSONArray ls = (JSONArray)json.get(src$n);
        JSONObject json$ = (JSONObject) ls.get(0);
        String out = (String) json$.get(in);
        if(out == null)
        {
            throw new EncException("Invalid Index");
        }
        return out;

    }
    public String ls() throws EncException {
        String ls = (String) json.get("ls");
        if(ls == null)
        {
            throw new EncException("ls not found in alg");
        }
        return ls;
    }
    public String get(String msg)
    {
        return (String)json.get(msg);
    }
}
