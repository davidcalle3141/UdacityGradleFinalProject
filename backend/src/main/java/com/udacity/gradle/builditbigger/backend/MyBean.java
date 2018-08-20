package com.udacity.gradle.builditbigger.backend;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String[] myData;

    public String[] getData() {
        return myData;
    }

    public void setData(String[] data) {
        if(data==null){
            data = new String[2];
            data[0]="programming";
            data[1]="Knock knock.\n" +
                    "\n" +
                    "Race condition.\n" +
                    "\n" +
                    "Who's there?";
        }
        myData = data;
    }

}