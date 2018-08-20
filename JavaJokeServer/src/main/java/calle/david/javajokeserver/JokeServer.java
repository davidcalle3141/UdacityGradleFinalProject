package calle.david.javajokeserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import sun.rmi.runtime.Log;

public class JokeServer {
    private Random random;
    public JokeServer(){
        random = new Random();
    }

    public String[] retrieveRandomJoke(){
        int randomNum = random.nextInt((1650 - 1) + 1) + 1;
        String jokeAry[] = new String[2];
        String resPath = "/JokesJson/jokes.json";
        int count =0;


        try{
            InputStream stream = getClass().getResourceAsStream(resPath);
            JsonReader reader = new JsonReader(new
                    InputStreamReader(stream,"UTF-8"));
            Gson gson = new GsonBuilder().create();
            reader.beginArray();
            while(reader.hasNext()){
                count++;
                Joke joke = gson.fromJson(reader,Joke.class);
                if(randomNum==count && !joke.body.trim().equals("")){
                    jokeAry[0]= String.valueOf(randomNum);
                    jokeAry[1]=joke.body;
                    break;
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return jokeAry;
    }

}
