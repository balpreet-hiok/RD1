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

}
