package org.bstechnologies.rd1;

import org.bstechnologies.rd1.alg.FrmtAlg;
import org.bstechnologies.rd1.enc.Encrypter;
import org.bstechnologies.rd1.io.EncException;
import org.bstechnologies.rd1.io.Reader;
import org.bstechnologies.rd1.keys.KeyGenerator;
import org.json.simple.JSONObject;

import java.io.FileWriter;

public class RD1 {
    Encrypter enc;
    String path;
    public RD1(String path)
    {
        enc = new Encrypter(path);
        this.path = path;
    }
    public String encrypt(String msg) throws EncException {
        return enc.encrypt(msg);
    }
    public void format() throws EncException {
        FrmtAlg frmt = new FrmtAlg(path);
    }
    public String decrypt(String encryption) throws EncException {
        return enc.decrypt(encryption);

    }

    public static void main(String[] args) throws Exception {
        KeyGenerator keygen = new KeyGenerator(4,5);
//        keygen.genKey("src/org/bstechnologies/rd1/alg/test.json");
        String msg = "Balpreet Singh";
        System.out.println(new RD1("src/org/bstechnologies/rd1/alg/test.json").encrypt(msg));
        System.out.println(new RD1("src/org/bstechnologies/rd1/alg/test.json").decrypt("56282z41e2TR74W2jk411c0856OOy8566wVW41tZqT7472Dc410110564W7N564h6041242W742tot415u8q"));
    }
}
