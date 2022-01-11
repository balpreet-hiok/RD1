# RD1
RD1, Java Encryption Project. Made by Balpreet

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

# v1.0
This release brings a KeyGenerator file which allows you to create unique alg files which are the secret of your encryption. If anyone gets this alg fil, he will be able to decrypt your messages. To use KeyGenerator, use:-

```
KeyGenerator keygen = new KeyGenerator(ls,alg);
keygen.genKey(path);       //Path where you want 
```
to store the secret alg file
RD-1 generates more than 1 encryption, as many as you want. So to define how many encryption tables to make, `ls` is used
Usually, you can enter 3 to 4 in `ls` but you can enter as many as you want.
`alg` is variable which defines how long alg sequence to make
Eg:-
If you enter `5` in `ls`, then you go to file where you have stored and search `"ls": "`you will get value 10 long. Because each Encryption table
is identifid by 2 digits long number. So 2*5 is 10.
Lets say you get `4174615639` So here `41` `74` `61` `56` `39` identifies different tables. Now in which order to encrypt the String is defined by `alg`
Lets say if you want to first encrypt from `39` then `41,74,56,61` . You just find `"alg":` in secret file and enter `3941745661` value corresponding it.
You can repeat same table again but overall legnth should not be less the legnth of ls
You can make like `39413974`
Or you just enter how long you want while defining `KeyGenerator` in `alg` variable.
Note `alg` must be greater than equal to `ls`