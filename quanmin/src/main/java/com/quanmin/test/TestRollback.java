package com.quanmin.test;

public class TestRollback {

    public static void main(String[] args) {
        boolean smark=false;
        if (smark){
            try {

            }finally {
                System.out.println(111);
            }
        }
    }

}
