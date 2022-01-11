package org.bstechnologies.rd1.enc;

import org.bstechnologies.rd1.io.EncException;
import org.bstechnologies.rd1.io.Reader;

import java.util.Arrays;

public class Encrypter {
    Reader reader;

    public Encrypter(String path) {
        reader = new Reader(path);

    }

    public String[] alg() throws EncException {
        String alg1 = reader.getAlg();
        String[] out = {};
        out = Arrays.copyOf(out, alg1.length() / 2);
        int in = 0;
        for (int i = 0; i < alg1.length() / 2; i++,in+=2) {
            String temp = alg1;
            temp = temp.substring(in, in + 2);
            out[i] = temp;
        }
        return out;
    }

    public String[] ls() throws EncException {
        String alg1 = reader.ls();
        String[] out = {};
        out = Arrays.copyOf(out, alg1.length()/2);
        int in = 0;
        int in$ = 2;
        for (int i = 0; i < out.length; i++) {
            String temp = alg1;
            temp = temp.replace(temp.substring(in$), "");
            temp = temp.substring(in);
            out[i] = temp;
            in += 2;
            in$ += 2;
        }
        return out;
    }

    public String encrypt(String raw) throws EncException {
        String[] alg = alg();
        String out = "";
        int in = 0;
        for (int i = 0; i < raw.length(); i++) {
            char ch = raw.charAt(i);

            String enc = reader.enc(alg[in], Character.toString(ch));
            out = out + alg[in] + enc;


            // Repeating same Alg again and again
            in++;
            if (in == alg.length) in = 0;
        }
        return out;
    }

    public String decrypt(String encryption) throws EncException {
        String enc = encryption;
        String[] out = {};
        out = Arrays.copyOf(out, enc.length() / 6);
        int in = 0;
        for (int i=0;i<enc.length()/6;i++)
        {
            String temp=enc;
            temp = temp.substring(in);
            temp=temp.replace(temp.substring(6),"");
            out[i] = temp;
            in+=6;

        }

        String [] src = new String[out.length];
        String [] raw = new String[src.length];
        for(int i=0;i<raw.length;i++)
        {
            String temp=out[i];
            temp = temp.substring(2);
            raw[i]=temp;
            temp=out[i];
            temp=temp.replace(temp.substring(2),"");
            src[i]=temp;
        }
        String result="";
        for(int i=0;i<raw.length;i++)
        {
            if(raw[i].equals("0110"))
            {
                result+=" ";
                continue;
            }
            String temp=reader.enc(src[i],raw[i]);
            result+=temp;
        }
        return result;
    }

}

