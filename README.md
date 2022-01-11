# RD1
RD1 is an encryption. Made by Balpreet

# Usage
1. Add the jar file in out / artifacts / RD_1_jar to libraries
2. Follow the code

```
RD1 rd1 = new RD1(path); // path to the alg file. One is already made in org/bstechnologies/rd1/alg/alg.json
rd1.format();  // This formats to alg file according to use. You can Encrypt msg without formatting but can't decrypt without it.
String enc = "Balpreet";
enc = rd1.encrypt(enc);
System.out.println(enc);
String dec = rd1.decrypt(enc);
System.out.println(dec);
```

Don't worry, I will soon add a program which makes new keys on own
