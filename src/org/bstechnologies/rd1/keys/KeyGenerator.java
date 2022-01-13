package org.bstechnologies.rd1.keys;

import org.bstechnologies.rd1.alg.FrmtAlg;
import org.bstechnologies.rd1.io.EncException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
    public KeyGenerator(){}
    public void genKey(String path) throws IOException, EncException {
        JSONObject json = new JSONObject();
        json = generate(json);
        File file = new File(path);
        if(!file.exists())
        {
            boolean result = file.createNewFile();
        }
        FileWriter fw = new FileWriter(path);
        fw.write(json.toJSONString());
        fw.close();
        FrmtAlg frmt = new FrmtAlg(path);

    }
    public JSONObject genKey(JSONObject json) throws IOException, EncException {
        json = generate(json);
        FrmtAlg frmt = new FrmtAlg(json);
        json=frmt.getJSON();
        return json;
    }
    public JSONObject generate(JSONObject json)
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
    public void algtokey(JSONObject json,String out) throws IOException, ParseException {
      JSONParser parser =  new JSONParser();
      File file = new File(out);
      if(!file.exists())
      {
          boolean exists = file.createNewFile();

      }
      Object obj1 = parser.parse("{\"00101100\":\"I\",\"00100011\":\"R\",\"00100010\":\"S\",\"00000001\":\"z\",\"isto\":\"01000000\",\"00011011\":\"Z\",\"00000010\":\"y\",\"00100001\":\"T\",\"00100000\":\"U\",\"01000101\":\"n\",\"00001100\":\"o\",\"00000011\":\"x\",\"00110101\":\"1\",\"00110100\":\"A\",\"00111111\":\"alg\",\"1\":\"00110101\",\"2\":\"00110110\",\"3\":\"00110111\",\"4\":\"00111000\",\"5\":\"00111001\",\"6\":\"00111010\",\"7\":\"00111011\",\"8\":\"00111100\",\"01000100\":\"m\",\"9\":\"00111101\",\"00100111\":\"N\",\"A\":\"00110100\",\"B\":\"00110011\",\"00101000\":\"M\",\"C\":\"00110010\",\"00101001\":\"L\",\"D\":\"00110001\",\"E\":\"00110000\",\"F\":\"00101111\",\"G\":\"00101110\",\"00001111\":\"l\",\"H\":\"00101101\",\"I\":\"00101100\",\"J\":\"00101011\",\"00111001\":\"5\",\"K\":\"00101010\",\"L\":\"00101001\",\"01000000\":\"isto\",\"M\":\"00101000\",\"N\":\"00100111\",\"O\":\"00100110\",\"P\":\"00100101\",\"Q\":\"00100100\",\"R\":\"00100011\",\"S\":\"00100010\",\"01000001\":\"define\",\"T\":\"00100001\",\"00111010\":\"6\",\"U\":\"00100000\",\"00111011\":\"7\",\"V\":\"00011111\",\"00010101\":\"f\",\"W\":\"00011110\",\"00010100\":\"g\",\"X\":\"00011101\",\"00001000\":\"s\",\"Y\":\"00011100\",\"Z\":\"00011011\",\"a\":\"00011010\",\"b\":\"00011001\",\"00101110\":\"G\",\"c\":\"00011000\",\"00101111\":\"F\",\"d\":\"00010111\",\"e\":\"00010110\",\"f\":\"00010101\",\"g\":\"00010100\",\"00001001\":\"r\",\"h\":\"00010011\",\"i\":\"00010010\",\"j\":\"00010001\",\"k\":\"00010000\",\"l\":\"00001111\",\"m\":\"01000100\",\"n\":\"01000101\",\"o\":\"00001100\",\"p\":\"00001011\",\"q\":\"01000011\",\"r\":\"00001001\",\"s\":\"00001000\",\"t\":\"00000111\",\"00010110\":\"e\",\"u\":\"00000110\",\"v\":\"00000101\",\"w\":\"00000100\",\"00010111\":\"d\",\"x\":\"00000011\",\"00001011\":\"p\",\"y\":\"00000010\",\"01000011\":\"q\",\"z\":\"00000001\",\"00101101\":\"H\",\"ls\":\"00111110\",\"00111100\":\"8\",\"00110011\":\"B\",\"00111101\":\"9\",\"00010000\":\"k\",\"00111110\":\"ls\",\"00010001\":\"j\",\"alg\":\"00111111\",\"00101010\":\"K\",\"00101011\":\"J\",\"00011100\":\"Y\",\"00010011\":\"h\",\"00111000\":\"4\",\"00010010\":\"i\",\"00011101\":\"X\",\"00100110\":\"O\",\"00000101\":\"v\",\"00000100\":\"w\",\"00110110\":\"2\",\"00110111\":\"3\",\"00011111\":\"V\",\"00011110\":\"W\",\"define\":\"01000001\",\"00100101\":\"P\",\"00100100\":\"Q\",\"00000111\":\"t\",\"00000110\":\"u\",\"00110000\":\"E\",\"00011010\":\"a\",\"00110001\":\"D\",\"00011001\":\"b\",\"00110010\":\"C\",\"00011000\":\"c\",\"definend\":\"01000010\",\"01000010\":\"definend\",\"0\":\"01000110\",\"01000110\":\"0\"}");
      JSONObject json1 = (JSONObject) obj1;
        String ls$ =(String) json.get("ls");
        String [] ls = new String[ls$.length()];
        for(int i=0,in=0;i<ls.length;i++,in++)
        {
            String temp=ls$;
            temp=temp.substring(in,in+1);
            ls[i]=temp;
        }
        String alg$=(String)json.get("alg");
        String [] alg = new String[alg$.length()];
        for(int i=0,in=0;i<alg.length;i++,in++)
        {
            String temp=alg$;
            temp = temp.substring(in,in+1);
            alg[i]=temp;
        }
        String isto = (String)json1.get("isto");
        isto=bintoStr(isto);
        String ch1 = (String)json1.get("alg");
        ch1=bintoStr(ch1);
        String tempp = "";
        tempp=ch1+isto;
        for(int i=0;i<alg.length;i++)
        {
            String temp=(String)json1.get(alg[i]);
            temp=bintoStr(temp);
            tempp=tempp+temp;
        }

        FileWriter fw = new FileWriter(out);
        fw.write(tempp+"\n");
        ch1= (String)json1.get("ls");
        ch1=bintoStr(ch1);
        tempp="";
        tempp=tempp+ch1;
        tempp=tempp+isto;
        for(int i=0;i<ls.length;i++)
        {
            String temp=(String)json1.get(ls[i]);
            temp=bintoStr(temp);
            tempp=tempp+temp;
        }
        fw.write(tempp+"\n");
        String [] aa = new String[ls$.length()/2];
        String [] ab = new String[aa.length];
        String [] ac= new String[aa.length];
        for(int i=0,in=0;i<ac.length;i++,in+=2)
        {
            String temp=ls$;
            temp=temp.substring(in,in+2);
            ac[i]=temp;
        }
        String [] ad = new String[ac.length];
        for(int i=0,in=0;i<ad.length;i++,in+=2)
        {
            String temp=ls$;
            temp=temp.substring(in,in+2);
            String out$= (String)json.get(temp);
            ad[i]=out$;
        }
        for(int i=0;i<ac.length;i++)
        {
            String output = "";
            for(int j=0;j<ac[i].length();j++)
            {
               String temp=ac[i];
               String ae = Character.toString(temp.charAt(j));
               String af =(String) json1.get(ae);
               String ag = bintoStr(af);
               output=output+ag;
            }
            aa[i]=output;
        }
        for(int i=0;i<ad.length;i++)
        {
            String output="";
            for(int j=0;j<ad[i].length();j++)
            {
                String temp=ad[i];
                String ae = Character.toString(temp.charAt(j));
                String af = (String) json1.get(ae);
                String ag = bintoStr(af);
                output=output+ag;
            }
            ab[i]=output;

        }
        for(int i=0;i<aa.length;i++)
        {
            String temp=aa[i]+isto+ab[i];
            fw.write(temp+"\n");

        }
        for(int i=0;i<ad.length;i++)
        {
            String test = "";
            String test2 = "";
            String ae =(String) json1.get("define");
            ae=bintoStr(ae);
            fw.write(ae+ab[i]+"\n");
            JSONArray array = (JSONArray) json.get(ad[i]);
            JSONObject json$ = (JSONObject) array.get(0);
            Set key = json$.keySet();
            String [] af = new String[key.size()];
            key.toArray(af);
            for(int j=0;j<af.length;j++)
            {
                String ag = "";
                String hh = "";
                test=af[j];
                ag = af[j];
                for(int k=0;k<ag.length();k++)
                {
                    String ah = Character.toString(ag.charAt(k));
                    ah =(String) json1.get(ah);
                    ah = bintoStr(ah);
                    hh=hh+ah;
                }
                ag=hh;
                String ah = "";
                String ai = (String)json$.get(af[j]);
                for(int k=0;k<ai.length();k++)
                {
                    String aj = ai;
                    aj = Character.toString(aj.charAt(k));
                    aj =(String) json1.get(aj);
                    aj = bintoStr(aj);
                    ah = ah+aj;
                }
                fw.write(ag+isto+ah+"\n");
            }
            String defnd =(String) json1.get("definend");
            defnd = bintoStr(defnd);
            fw.write(defnd+"\n");
        }
        fw.close();

    }
    public void algtokey(JSONObject json) throws IOException, ParseException {
        JSONParser parser =  new JSONParser();
        String out = "secret.key";
        File file = new File(out);
        if(!file.exists())
        {
            boolean exists = file.createNewFile();

        }
        Object obj1 = parser.parse("{\"00101100\":\"I\",\"00100011\":\"R\",\"00100010\":\"S\",\"00000001\":\"z\",\"isto\":\"01000000\",\"00011011\":\"Z\",\"00000010\":\"y\",\"00100001\":\"T\",\"00100000\":\"U\",\"01000101\":\"n\",\"00001100\":\"o\",\"00000011\":\"x\",\"00110101\":\"1\",\"00110100\":\"A\",\"00111111\":\"alg\",\"1\":\"00110101\",\"2\":\"00110110\",\"3\":\"00110111\",\"4\":\"00111000\",\"5\":\"00111001\",\"6\":\"00111010\",\"7\":\"00111011\",\"8\":\"00111100\",\"01000100\":\"m\",\"9\":\"00111101\",\"00100111\":\"N\",\"A\":\"00110100\",\"B\":\"00110011\",\"00101000\":\"M\",\"C\":\"00110010\",\"00101001\":\"L\",\"D\":\"00110001\",\"E\":\"00110000\",\"F\":\"00101111\",\"G\":\"00101110\",\"00001111\":\"l\",\"H\":\"00101101\",\"I\":\"00101100\",\"J\":\"00101011\",\"00111001\":\"5\",\"K\":\"00101010\",\"L\":\"00101001\",\"01000000\":\"isto\",\"M\":\"00101000\",\"N\":\"00100111\",\"O\":\"00100110\",\"P\":\"00100101\",\"Q\":\"00100100\",\"R\":\"00100011\",\"S\":\"00100010\",\"01000001\":\"define\",\"T\":\"00100001\",\"00111010\":\"6\",\"U\":\"00100000\",\"00111011\":\"7\",\"V\":\"00011111\",\"00010101\":\"f\",\"W\":\"00011110\",\"00010100\":\"g\",\"X\":\"00011101\",\"00001000\":\"s\",\"Y\":\"00011100\",\"Z\":\"00011011\",\"a\":\"00011010\",\"b\":\"00011001\",\"00101110\":\"G\",\"c\":\"00011000\",\"00101111\":\"F\",\"d\":\"00010111\",\"e\":\"00010110\",\"f\":\"00010101\",\"g\":\"00010100\",\"00001001\":\"r\",\"h\":\"00010011\",\"i\":\"00010010\",\"j\":\"00010001\",\"k\":\"00010000\",\"l\":\"00001111\",\"m\":\"01000100\",\"n\":\"01000101\",\"o\":\"00001100\",\"p\":\"00001011\",\"q\":\"01000011\",\"r\":\"00001001\",\"s\":\"00001000\",\"t\":\"00000111\",\"00010110\":\"e\",\"u\":\"00000110\",\"v\":\"00000101\",\"w\":\"00000100\",\"00010111\":\"d\",\"x\":\"00000011\",\"00001011\":\"p\",\"y\":\"00000010\",\"01000011\":\"q\",\"z\":\"00000001\",\"00101101\":\"H\",\"ls\":\"00111110\",\"00111100\":\"8\",\"00110011\":\"B\",\"00111101\":\"9\",\"00010000\":\"k\",\"00111110\":\"ls\",\"00010001\":\"j\",\"alg\":\"00111111\",\"00101010\":\"K\",\"00101011\":\"J\",\"00011100\":\"Y\",\"00010011\":\"h\",\"00111000\":\"4\",\"00010010\":\"i\",\"00011101\":\"X\",\"00100110\":\"O\",\"00000101\":\"v\",\"00000100\":\"w\",\"00110110\":\"2\",\"00110111\":\"3\",\"00011111\":\"V\",\"00011110\":\"W\",\"define\":\"01000001\",\"00100101\":\"P\",\"00100100\":\"Q\",\"00000111\":\"t\",\"00000110\":\"u\",\"00110000\":\"E\",\"00011010\":\"a\",\"00110001\":\"D\",\"00011001\":\"b\",\"00110010\":\"C\",\"00011000\":\"c\",\"definend\":\"01000010\",\"01000010\":\"definend\",\"0\":\"0100011\",\"0100011\":\"0\"}");
        JSONObject json1 = (JSONObject) obj1;
        String ls$ =(String) json.get("ls");
        String [] ls = new String[ls$.length()];
        for(int i=0,in=0;i<ls.length;i++,in++)
        {
            String temp=ls$;
            temp=temp.substring(in,in+1);
            ls[i]=temp;
        }
        String alg$=(String)json.get("alg");
        String [] alg = new String[alg$.length()];
        for(int i=0,in=0;i<alg.length;i++,in++)
        {
            String temp=alg$;
            temp = temp.substring(in,in+1);
            alg[i]=temp;
        }
        String isto = (String)json1.get("isto");
        isto=bintoStr(isto);
        String ch1 = (String)json1.get("alg");
        ch1=bintoStr(ch1);
        String tempp = "";
        tempp=ch1+isto;
        for(int i=0;i<alg.length;i++)
        {
            String temp=(String)json1.get(alg[i]);
            temp=bintoStr(temp);
            tempp=tempp+temp;
        }

        FileWriter fw = new FileWriter(out);
        fw.write(tempp+"\n");
        ch1= (String)json1.get("ls");
        ch1=bintoStr(ch1);
        tempp="";
        tempp=tempp+ch1;
        tempp=tempp+isto;
        for(int i=0;i<ls.length;i++)
        {
            String temp=(String)json1.get(ls[i]);
            temp=bintoStr(temp);
            tempp=tempp+temp;
        }
        fw.write(tempp+"\n");
        String [] aa = new String[ls$.length()/2];
        String [] ab = new String[aa.length];
        String [] ac= new String[aa.length];
        for(int i=0,in=0;i<ac.length;i++,in+=2)
        {
            String temp=ls$;
            temp=temp.substring(in,in+2);
            ac[i]=temp;
        }
        String [] ad = new String[ac.length];
        for(int i=0,in=0;i<ad.length;i++,in+=2)
        {
            String temp=ls$;
            temp=temp.substring(in,in+2);
            String out$= (String)json.get(temp);
            ad[i]=out$;
        }
        for(int i=0;i<ac.length;i++)
        {
            String output = "";
            for(int j=0;j<ac[i].length();j++)
            {
                String temp=ac[i];
                String ae = Character.toString(temp.charAt(j));
                String af =(String) json1.get(ae);
                String ag = bintoStr(af);
                output=output+ag;
            }
            aa[i]=output;
        }
        for(int i=0;i<ad.length;i++)
        {
            String output="";
            for(int j=0;j<ad[i].length();j++)
            {
                String temp=ad[i];
                String ae = Character.toString(temp.charAt(j));
                String af = (String) json1.get(ae);
                String ag = bintoStr(af);
                output=output+ag;
            }
            ab[i]=output;

        }
        for(int i=0;i<aa.length;i++)
        {
            String temp=aa[i]+isto+ab[i];
            fw.write(temp+"\n");

        }
        for(int i=0;i<ad.length;i++)
        {
            String test = "";
            String test2 = "";
            String ae =(String) json1.get("define");
            ae=bintoStr(ae);
            fw.write(ae+ab[i]+"\n");
            JSONArray array = (JSONArray) json.get(ad[i]);
            JSONObject json$ = (JSONObject) array.get(0);
            Set key = json$.keySet();
            String [] af = new String[key.size()];
            key.toArray(af);
            for(int j=0;j<af.length;j++)
            {
                String ag = "";
                String hh = "";
                test=af[j];
                ag = af[j];
                for(int k=0;k<ag.length();k++)
                {
                    String ah = Character.toString(ag.charAt(k));
                    ah =(String) json1.get(ah);
                    ah = bintoStr(ah);
                    hh=hh+ah;
                }
                ag=hh;
                String ah = "";
                String ai = (String)json$.get(af[j]);
                test2 = (String)json$.get(af[j]);
                for(int k=0;k<ai.length();k++)
                {
                    String aj = ai;
                    aj = Character.toString(aj.charAt(k));
                    aj =(String) json1.get(aj);
                    aj = bintoStr(aj);
                    ah = ah+aj;
                }
                fw.write(ag+isto+ah+"\n");
            }
            String defnd =(String) json1.get("definend");
            defnd = bintoStr(defnd);
            fw.write(defnd+"\n");
        }
        fw.close();

    }
    public void keytoalg(String path,String out) throws IOException, ParseException {
       Scanner scan = new Scanner(new File(path));
       JSONParser parser = new JSONParser();
        Object obj1 = parser.parse("{\"0\":\"01000110\",\"01000110\":\"0\"\"00101100\":\"I\",\"00100011\":\"R\",\"00100010\":\"S\",\"00000001\":\"z\",\"isto\":\"01000000\",\"00011011\":\"Z\",\"00000010\":\"y\",\"00100001\":\"T\",\"00100000\":\"U\",\"01000101\":\"n\",\"00001100\":\"o\",\"00000011\":\"x\",\"00110101\":\"1\",\"00110100\":\"A\",\"00111111\":\"alg\",\"1\":\"00110101\",\"2\":\"00110110\",\"3\":\"00110111\",\"4\":\"00111000\",\"5\":\"00111001\",\"6\":\"00111010\",\"7\":\"00111011\",\"8\":\"00111100\",\"01000100\":\"m\",\"9\":\"00111101\",\"00100111\":\"N\",\"A\":\"00110100\",\"B\":\"00110011\",\"00101000\":\"M\",\"C\":\"00110010\",\"00101001\":\"L\",\"D\":\"00110001\",\"E\":\"00110000\",\"F\":\"00101111\",\"G\":\"00101110\",\"00001111\":\"l\",\"H\":\"00101101\",\"I\":\"00101100\",\"J\":\"00101011\",\"00111001\":\"5\",\"K\":\"00101010\",\"L\":\"00101001\",\"01000000\":\"isto\",\"M\":\"00101000\",\"N\":\"00100111\",\"O\":\"00100110\",\"P\":\"00100101\",\"Q\":\"00100100\",\"R\":\"00100011\",\"S\":\"00100010\",\"01000001\":\"define\",\"T\":\"00100001\",\"00111010\":\"6\",\"U\":\"00100000\",\"00111011\":\"7\",\"V\":\"00011111\",\"00010101\":\"f\",\"W\":\"00011110\",\"00010100\":\"g\",\"X\":\"00011101\",\"00001000\":\"s\",\"Y\":\"00011100\",\"Z\":\"00011011\",\"a\":\"00011010\",\"b\":\"00011001\",\"00101110\":\"G\",\"c\":\"00011000\",\"00101111\":\"F\",\"d\":\"00010111\",\"e\":\"00010110\",\"f\":\"00010101\",\"g\":\"00010100\",\"00001001\":\"r\",\"h\":\"00010011\",\"i\":\"00010010\",\"j\":\"00010001\",\"k\":\"00010000\",\"l\":\"00001111\",\"m\":\"01000100\",\"n\":\"01000101\",\"o\":\"00001100\",\"p\":\"00001011\",\"q\":\"01000011\",\"r\":\"00001001\",\"s\":\"00001000\",\"t\":\"00000111\",\"00010110\":\"e\",\"u\":\"00000110\",\"v\":\"00000101\",\"w\":\"00000100\",\"00010111\":\"d\",\"x\":\"00000011\",\"00001011\":\"p\",\"y\":\"00000010\",\"01000011\":\"q\",\"z\":\"00000001\",\"00101101\":\"H\",\"ls\":\"00111110\",\"00111100\":\"8\",\"00110011\":\"B\",\"00111101\":\"9\",\"00010000\":\"k\",\"00111110\":\"ls\",\"00010001\":\"j\",\"alg\":\"00111111\",\"00101010\":\"K\",\"00101011\":\"J\",\"00011100\":\"Y\",\"00010011\":\"h\",\"00111000\":\"4\",\"00010010\":\"i\",\"00011101\":\"X\",\"00100110\":\"O\",\"00000101\":\"v\",\"00000100\":\"w\",\"00110110\":\"2\",\"00110111\":\"3\",\"00011111\":\"V\",\"00011110\":\"W\",\"define\":\"01000001\",\"00100101\":\"P\",\"00100100\":\"Q\",\"00000111\":\"t\",\"00000110\":\"u\",\"00110000\":\"E\",\"00011010\":\"a\",\"00110001\":\"D\",\"00011001\":\"b\",\"00110010\":\"C\",\"00011000\":\"c\",\"definend\":\"01000010\",\"01000010\":\"definend\",\"0\":\"0100011\",\"0100011\":\"0\"}");
        JSONObject json1 = (JSONObject) obj1;
        JSONObject json = new JSONObject();
        int session=0;
        boolean skip=false;
        int i=0;
        String [] list =new String[105];
       while(scan.hasNext())
       {
         String aa = scan.nextLine();
         String ac = "";
         String ad = "";

         for(int j=0;j<aa.length();j++)
         {
             if(skip)
             {
                 break;
             }
             String ab = Character.toString(aa.charAt(j));
             ab = toBinary(ab);
             ab = (String) json1.get(ab);
             if(ab.equals("define"))
             {
                 skip=true;
                 break;
             }
             if(ab.equals("isto"))
             {
                 session=j+1;
                 break;
             }
             ac=ac+ab;

         }
         for(int j=session;j<aa.length();j++) {
             if (skip) {
                 break;
             }
             String ab = Character.toString(aa.charAt(j));
             ab = toBinary(ab);
             ab = (String) json1.get(ab);
             ad = ad + ab;
         }

           if(skip)
           {
               JSONObject json$ = new JSONObject();
               JSONArray arr = new JSONArray();
               String al = "";
               String af = aa;af=Character.toString(af.charAt(0));af=toBinary(af);af=(String)json1.get(af);
               if(af.equals("definend"))
               {
                   String ak = list[0];ak=ak.substring(1);
                   json$=new JSONObject();
                   arr=new JSONArray();
                   al = "";
                   String ai = list[0];
                   ai=ai.substring(1);
                   String aj="";
                   for(int k=0;k<ai.length();k++)
                   {
                       String am = Character.toString(ai.charAt(k));am=toBinary(am);am=(String) json1.get(am);
                       aj=aj+am;
                   }
                   for(int j=1;j<list.length;j++)
                   {
                       int ses=0;
                       String ag="";
                       String ah="";
                       for(int k=0;k<list[j].length();k++)
                       {
                           String an = Character.toString(list[j].charAt(k));an=toBinary(an);an=(String)json1.get(an);
                           if(an.equals("isto"))
                           {
                               ses=k+1;
                               break;
                           }
                           ag=ag+an;
                       }
                       for(int k=ses;k<list[j].length();k++)
                       {
                           String ao = Character.toString(list[j].charAt(k));ao=toBinary(ao);ao=(String)json1.get(ao);
                           ah=ah+ao;
                       }
                       json$.put(ag,ah);
                   }
                   arr.add(json$);
                   json.put(aj,arr);
                   skip=false;
                   i=0;
                   continue;
               }
               list[i]= aa;
               i++;
           }
         if(!skip)
         {
             json.put(ac,ad);
         }

       }
       FileWriter fw = new FileWriter(out);
       fw.write(json.toJSONString());
       fw.close();
    }
    public JSONObject keytoalg(String path) throws IOException, ParseException {
        Scanner scan = new Scanner(new File(path));
        JSONParser parser = new JSONParser();
        Object obj1 = parser.parse("{\"0\":\"01000110\",\"01000110\":\"0\"\"00101100\":\"I\",\"00100011\":\"R\",\"00100010\":\"S\",\"00000001\":\"z\",\"isto\":\"01000000\",\"00011011\":\"Z\",\"00000010\":\"y\",\"00100001\":\"T\",\"00100000\":\"U\",\"01000101\":\"n\",\"00001100\":\"o\",\"00000011\":\"x\",\"00110101\":\"1\",\"00110100\":\"A\",\"00111111\":\"alg\",\"1\":\"00110101\",\"2\":\"00110110\",\"3\":\"00110111\",\"4\":\"00111000\",\"5\":\"00111001\",\"6\":\"00111010\",\"7\":\"00111011\",\"8\":\"00111100\",\"01000100\":\"m\",\"9\":\"00111101\",\"00100111\":\"N\",\"A\":\"00110100\",\"B\":\"00110011\",\"00101000\":\"M\",\"C\":\"00110010\",\"00101001\":\"L\",\"D\":\"00110001\",\"E\":\"00110000\",\"F\":\"00101111\",\"G\":\"00101110\",\"00001111\":\"l\",\"H\":\"00101101\",\"I\":\"00101100\",\"J\":\"00101011\",\"00111001\":\"5\",\"K\":\"00101010\",\"L\":\"00101001\",\"01000000\":\"isto\",\"M\":\"00101000\",\"N\":\"00100111\",\"O\":\"00100110\",\"P\":\"00100101\",\"Q\":\"00100100\",\"R\":\"00100011\",\"S\":\"00100010\",\"01000001\":\"define\",\"T\":\"00100001\",\"00111010\":\"6\",\"U\":\"00100000\",\"00111011\":\"7\",\"V\":\"00011111\",\"00010101\":\"f\",\"W\":\"00011110\",\"00010100\":\"g\",\"X\":\"00011101\",\"00001000\":\"s\",\"Y\":\"00011100\",\"Z\":\"00011011\",\"a\":\"00011010\",\"b\":\"00011001\",\"00101110\":\"G\",\"c\":\"00011000\",\"00101111\":\"F\",\"d\":\"00010111\",\"e\":\"00010110\",\"f\":\"00010101\",\"g\":\"00010100\",\"00001001\":\"r\",\"h\":\"00010011\",\"i\":\"00010010\",\"j\":\"00010001\",\"k\":\"00010000\",\"l\":\"00001111\",\"m\":\"01000100\",\"n\":\"01000101\",\"o\":\"00001100\",\"p\":\"00001011\",\"q\":\"01000011\",\"r\":\"00001001\",\"s\":\"00001000\",\"t\":\"00000111\",\"00010110\":\"e\",\"u\":\"00000110\",\"v\":\"00000101\",\"w\":\"00000100\",\"00010111\":\"d\",\"x\":\"00000011\",\"00001011\":\"p\",\"y\":\"00000010\",\"01000011\":\"q\",\"z\":\"00000001\",\"00101101\":\"H\",\"ls\":\"00111110\",\"00111100\":\"8\",\"00110011\":\"B\",\"00111101\":\"9\",\"00010000\":\"k\",\"00111110\":\"ls\",\"00010001\":\"j\",\"alg\":\"00111111\",\"00101010\":\"K\",\"00101011\":\"J\",\"00011100\":\"Y\",\"00010011\":\"h\",\"00111000\":\"4\",\"00010010\":\"i\",\"00011101\":\"X\",\"00100110\":\"O\",\"00000101\":\"v\",\"00000100\":\"w\",\"00110110\":\"2\",\"00110111\":\"3\",\"00011111\":\"V\",\"00011110\":\"W\",\"define\":\"01000001\",\"00100101\":\"P\",\"00100100\":\"Q\",\"00000111\":\"t\",\"00000110\":\"u\",\"00110000\":\"E\",\"00011010\":\"a\",\"00110001\":\"D\",\"00011001\":\"b\",\"00110010\":\"C\",\"00011000\":\"c\",\"definend\":\"01000010\",\"01000010\":\"definend\",\"0\":\"0100011\",\"0100011\":\"0\"}");
        JSONObject json1 = (JSONObject) obj1;
        JSONObject json = new JSONObject();
        int session=0;
        boolean skip=false;
        int i=0;
        String [] list =new String[105];
        while(scan.hasNext())
        {
            String aa = scan.nextLine();
            String ac = "";
            String ad = "";

            for(int j=0;j<aa.length();j++)
            {
                if(skip)
                {
                    break;
                }
                String ab = Character.toString(aa.charAt(j));
                ab = toBinary(ab);
                ab = (String) json1.get(ab);
                if(ab.equals("define"))
                {
                    skip=true;
                    break;
                }
                if(ab.equals("isto"))
                {
                    session=j+1;
                    break;
                }
                ac=ac+ab;

            }
            for(int j=session;j<aa.length();j++) {
                if (skip) {
                    break;
                }
                String ab = Character.toString(aa.charAt(j));
                ab = toBinary(ab);
                ab = (String) json1.get(ab);
                ad = ad + ab;
            }

            if(skip)
            {
                JSONObject json$ = new JSONObject();
                JSONArray arr = new JSONArray();
                String al = "";
                String af = aa;af=Character.toString(af.charAt(0));af=toBinary(af);af=(String)json1.get(af);
                if(af.equals("definend"))
                {
                    String ak = list[0];ak=ak.substring(1);
                    json$=new JSONObject();
                    arr=new JSONArray();
                    al = "";
                    String ai = list[0];
                    ai=ai.substring(1);
                    String aj="";
                    for(int k=0;k<ai.length();k++)
                    {
                        String am = Character.toString(ai.charAt(k));am=toBinary(am);am=(String) json1.get(am);
                        aj=aj+am;
                    }
                    for(int j=1;j<list.length;j++)
                    {
                        int ses=0;
                        String ag="";
                        String ah="";
                        for(int k=0;k<list[j].length();k++)
                        {
                            String an = Character.toString(list[j].charAt(k));an=toBinary(an);an=(String)json1.get(an);
                            if(an.equals("isto"))
                            {
                                ses=k+1;
                                break;
                            }
                            ag=ag+an;
                        }
                        for(int k=ses;k<list[j].length();k++)
                        {
                            String ao = Character.toString(list[j].charAt(k));ao=toBinary(ao);ao=(String)json1.get(ao);
                            ah=ah+ao;
                        }
                        json$.put(ag,ah);
                    }
                    arr.add(json$);
                    json.put(aj,arr);
                    skip=false;
                    i=0;
                    continue;
                }
                list[i]= aa;
                i++;
            }
            if(!skip)
            {
                json.put(ac,ad);
            }

        }
        return json;
    }
    private String [] cleanArray(String[] array){

        Arrays.fill(array, null);
        return array;
    }
    private  String bintoStr(String input)
    {
        String raw = Arrays.stream(input.split(" "))
                .map(binary -> Integer.parseInt(binary, 2))
                .map(Character::toString)
                .collect(Collectors.joining());
        return raw;
    }
    public static String toBinary(String input) {

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
                            .replaceAll(" ", "0")                         // zero pads
            );
        }
        return orgBinary(result.toString(),8," ");

    }

    public static String orgBinary(String binary, int blockSize, String separator) {

        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));
    }
}
