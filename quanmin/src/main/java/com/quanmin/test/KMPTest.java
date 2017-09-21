package com.quanmin.test;

public class KMPTest {

    public static void main(String[] args) {
        String n="fdasnjdfnqwejobfwe viuwe fwefqwefq";
        String m="fq";
        char[] chars=n.toCharArray();
        char[] chars1=m.toCharArray();
        int count=0;
        loop1:
        for (int x=0; x < chars.length; x++) {
            count++;
            for (int i=0; i < chars1.length; i++) {
                if (chars1[i] == chars[i+x]) {
                    if (i==chars1.length-1){
                        System.out.println("find");
                        break loop1;
                    }
                } else {
                    continue loop1;
                }

            }
        }
        System.out.println(count);


    }
}
