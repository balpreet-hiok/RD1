package org.bstechnologies.rd1.keys;

import org.bstechnologies.rd1.alg.FrmtAlg;
import org.bstechnologies.rd1.enc.Encrypter;
import org.bstechnologies.rd1.io.EncException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class KeyGenerator {
    JSONObject json;
    int ls;
    int alg;
    public KeyGenerator(int ls,int alg) throws Exception {
        JSONObject json = new JSONObject();
        this.json=json;
        this.ls =ls;
        if(alg<ls)
        {
            throw new Exception("alg should not be less than ls");
        }
        this.alg=alg;
    }
    public void genKey(String path) throws IOException, EncException {
        JSONObject json = new JSONObject();
        json = generate(json);
        FileWriter fw = new FileWriter(path);
        fw.write(json.toJSONString());
        fw.close();
        FrmtAlg frmt = new FrmtAlg("src/org/bstechnologies/rd1/alg/test.json");

    }
    private JSONObject generate(JSONObject json)
    {
        Random rand = new Random();
        String []record = new String[ls];
        for(int i=0;i<record.length;i++)
        {
            int num1=  rand.nextInt(9);
            int num2 = rand.nextInt(9);
            String result = (new StringBuilder()).append(num1).append(num2).toString();
            record[i]=result;
            for(int j=0;j<record.length;j++)
            {
                if(record[j] == result)
                {
                     while(result != record[j] && num1==num2)
                     {
                          num1=  rand.nextInt(9);
                         num2 = rand.nextInt(9);
                         String num1$=Integer.toString(num1);
                         String num2$ = Integer.toString(num2);
                         result = (new StringBuilder()).append(num1).append(num2).toString();
                         System.out.println(result);
                     }
                }
            }
            record[i]=result;
        }
        String temp="";
        for(int i=0;i<record.length;i++){
            temp=temp+record[i];
        }
        json.put("ls",temp);
        String [] alg1 = new String[alg];
        String [] a = new String[alg1.length];
        for(int i=0;i<alg1.length;i++){
            int num = rand.nextInt(ls);
            a[i]= String.valueOf(num);
            boolean test = false;
            for(int j=0;j<a.length;j++)
            {
                if(j != 0 && a[j-1] == a[j])
                    {
                        if(a[j-2] == a[j])
                        {
                           while(true)
                           {
                               num = rand.nextInt(ls);
                               break;
                           }
                        }
                    }
            }
            alg1[i] = record[num];

        }
        temp="";

        for(int i=0;i<alg1.length;i++)
        {
            temp=temp+alg1[i];

        }
        json.put("alg",temp);
        String [] list = {
                "a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g",
                "h",
                "i",
                "j",
                "k",
                "l",
                "m",
                "n",
                "o",
                "p",
                "q",
                "r",
                "s",
                "t",
                "u",
                "v",
                "w",
                "x",
                "y",
                "z",
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
                "J",
                "K",
                "L",
                "M",
                "N",
                "O",
                "P",
                "Q",
                "R",
                "S",
                "T",
                "U",
                "V",
                "W",
                "X",
                "Y",
                "Z"
        };
        String [] brief = new String[list.length];
        temp="";
      String [] list2 = new String[ls];
      for(int k=0;k<record.length;k++)
      {
          int num = rand.nextInt(27,52);
          int num$ = rand.nextInt(27,52);
          String num$$ = list[num];
          String num$$$ = list[num$];
          String b$= num$$+num$$$;
          JSONArray array = new JSONArray();
          JSONObject json$ = new JSONObject();

          for(int j=0;j<brief.length;j++)
          {

              for(int i=0;i<4;i++)
              {
                  int num1 = rand.nextInt(2);
                  if(num1 == 0)
                  {
                      int num2 = rand.nextInt(9);
                      temp=temp+num2;
                  }
                  if(num1 == 1)
                  {
                      int num2 = rand.nextInt(52);
                      temp=temp+list[num2];
                  }


              }
              brief[j]=temp;
              temp="";
              json$.put(list[j],brief[j]);

          }
          array.add(json$);
          list2[k]=b$;
          json.put(b$,array);


      }
      for(int i=0;i<ls;i++)
      {
          json.put(record[i],list2[i]);
      }

        return json;
    }
    private String [] cleanArray(String[] array){

        Arrays.fill(array, null);
        return array;
    }
}
