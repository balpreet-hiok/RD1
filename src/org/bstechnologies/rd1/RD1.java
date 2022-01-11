package org.bstechnologies.rd1;

import org.bstechnologies.rd1.alg.FrmtAlg;
import org.bstechnologies.rd1.enc.Encrypter;
import org.bstechnologies.rd1.io.EncException;

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

    public static void main(String[] args) throws EncException {
       RD1 rd1 = new RD1("src/org/bstechnologies/rd1/alg/alg.json");

       String enc = rd1.encrypt("Balpreet Singh is a good boy");
        System.out.println(enc);
        rd1.format();
        System.out.println(rd1.decrypt(enc));

    }
}
