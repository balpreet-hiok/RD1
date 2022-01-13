package org.bstechnologies.rd1.alg;

import org.bstechnologies.rd1.enc.Encrypter;
import org.bstechnologies.rd1.io.EncException;
import org.bstechnologies.rd1.io.Reader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class FrmtAlg {
    JSONObject json;
    public FrmtAlg(String path) throws EncException {

     try{
         Reader reader = new Reader(path);
         JSONParser parser =  new JSONParser();
         FileReader reader1 = new FileReader(path);
         Object obj = parser.parse(reader1);
         JSONObject json = (JSONObject) obj;
         String [] list = new Encrypter(path).ls();
         JSONObject out = new JSONObject();

        for(int i=0;i<list.length;i++)
        {
            String a = (String) json.get(list[i]);
            JSONArray arr = (JSONArray)json.get(a);
            JSONObject temp = (JSONObject)arr.get(0);
            temp = format(temp);
            JSONArray array = new JSONArray();
            array.add(temp);
            json.put(a,array);

        }
        json.put("frmtd","true");
        FileWriter fw = new FileWriter(path);
        fw.write(json.toJSONString());
        fw.close();
//         JSONArray array = (JSONArray)json.get("AN");
//         JSONObject json$ = (JSONObject)array.get(0);
//         Set keys = json$.keySet();
//         String [] ls = new String[keys.size()];
//         keys.toArray(ls);
     }catch(IOException e)
     {

     }catch(ParseException e)
     {

     }


    }
    public  FrmtAlg(JSONObject json)
    {

            try{
                String [] list = new Encrypter(json).ls();
                JSONObject out = new JSONObject();

                for(int i=0;i<list.length;i++)
                {
                    String a = (String) json.get(list[i]);
                    JSONArray arr = (JSONArray)json.get(a);
                    JSONObject temp = (JSONObject)arr.get(0);
                    temp = format(temp);
                    JSONArray array = new JSONArray();
                    array.add(temp);
                    json.put(a,array);

                }
                json.put("frmtd","true");
                this.json=json;

            } catch (EncException e) {
                e.printStackTrace();
            }

    }

private JSONObject format(JSONObject json){
        Set keys = json.keySet();
        String [] ls = new String[keys.size()];
        keys.toArray(ls);
        for(int i=0;i<ls.length;i++){
          String temp = (String)json.get(ls[i]);
          json.put(temp,ls[i]);
        }
        return json;
}
public JSONObject getJSON()
{
    return this.json;
}
}
