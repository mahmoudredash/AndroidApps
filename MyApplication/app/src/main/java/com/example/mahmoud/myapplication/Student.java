package com.example.mahmoud.myapplication;

import java.util.regex.Pattern;

/**
 * Created by mahmoud on 25/12/17.
 */

public class Student {

    private int id;
    private String name;
    private String emali;
    private int number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmali() {
        return emali;
    }

    public void setEmali(String emali) {
        this.emali = emali;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }



    /***
     *  Create RGX From Data INTER DataBase
     * @param txt
     * @param pattern @see   txt = txt using or pattern = txt | number
     * @return true OR False
     */
    public boolean rgxData(String txt,String pattern)
    {
        boolean su=false;
                switch (pattern){
                    case "txt":
                        pattern="\\w+";
                        su=  Pattern.matches(pattern,txt);
                        break;
                    case "number":
                        pattern="\\d+";
                        su=  Pattern.matches(pattern,txt);
                        break;
                    case "email":
                        pattern="^\\w+@\\w+\\.[a-z09]+$";
                        su=  Pattern.matches(pattern,txt);
                        break;
                }
                return su;
    }
}
