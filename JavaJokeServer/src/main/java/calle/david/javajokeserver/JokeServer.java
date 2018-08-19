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

public class JokeServer {
    private Random random;
    public JokeServer(){
        random = new Random();
    }

    public String[] retrieveRandomJoke(){
        int randomNum = random.nextInt((1660 - 1) + 1) + 1;
        String jokeAry[] = new String[2];
        String resPath = "/JokesJson/jokes.json";


        try{
            InputStream stream = getClass().getResourceAsStream(resPath);
            JsonReader reader = new JsonReader(new
                    InputStreamReader(stream,"UTF-8"));
            Gson gson = new GsonBuilder().create();
            reader.beginArray();
            while(reader.hasNext()){
                Joke joke = gson.fromJson(reader,Joke.class);
                if(joke.id == randomNum && !joke.body.trim().equals("")){
                    jokeAry[0]=joke.title;
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
