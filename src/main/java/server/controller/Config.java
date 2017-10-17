package server.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class Config {

    private static String DATABASE_HOST;
    private static Integer DATABASE_PORT;


    /**
     *
     * @throws IOException
     */
    public void initConfig() throws IOException {
        //Init variables
        JsonObject json = new JsonObject();

        JsonParser parser = new JsonParser();

        //Open file
        //We get the system path to a file included in resources, which is java classpath and therefore included in the JAR package.
        InputStream input = this.getClass().getResourceAsStream("/config.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        //Init string variables
        StringBuffer stringBuffer = new StringBuffer();
        String str = "";

        //Read file one line at a time an concat them into a combined string
        while((str = reader.readLine()) != null){
            stringBuffer.append(str);
        }

        //Convert json to variables
        json = (JsonObject) parser.parse(stringBuffer.toString());

        //Set class variables
        DATABASE_HOST = json.get("DATABASE_HOST").toString().replace("\"", "");
        DATABASE_PORT = Integer.parseInt(json.get("DATABASE_PORT").toString().replace("\"", ""));
    }

    public static String getDatabaseHost(){
        return DATABASE_HOST;
    }
    public static Integer getDatabasePort(){
        return DATABASE_PORT;
    }
}