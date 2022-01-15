package org.bstechnologies.rd1;

import org.bstechnologies.rd1.alg.FrmtAlg;
import org.bstechnologies.rd1.enc.Encrypter;
import org.bstechnologies.rd1.io.EncException;
import org.bstechnologies.rd1.keys.KeyGenerator;
import org.json.simple.JSONObject;
public class RD1 {
    Encrypter enc;
    String path;
    public RD1()
    {}
    public RD1(String path) throws Exception {
        KeyGenerator keygen = new KeyGenerator();
        JSONObject json = keygen.keytoalg(path);
        this.enc=new Encrypter(json);
        this.path=path;
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
    public void genKey(String out) throws Exception {
        JSONObject json = new JSONObject();
        KeyGenerator keygen = new KeyGenerator(15,20);
        json = keygen.genKey(json);
        keygen.algtokey(json,out);

    }

}
