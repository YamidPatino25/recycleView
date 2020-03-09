package com.example.recycleview.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DailyWeather {

    public String tempMax;
    public String tempMin;
    public String temp;
    public String main;
    public String description;
    public String humidity;
    public String date;
    public String picture;
    public String name;

    public static ArrayList<DailyWeather> getDailyWeather(JSONObject response){
        ArrayList<DailyWeather>days=new ArrayList<>();

        try {
            JSONArray info=response.getJSONArray("list");
            for (int i=0;i<info.length();i++){
                DailyWeather aux=new DailyWeather();
                aux.temp=info.getJSONObject(i).getJSONObject("main").getString("temp");
                aux.tempMax=info.getJSONObject(i).getJSONObject("main").getString("temp_min");
                aux.tempMin=info.getJSONObject(i).getJSONObject("main").getString("temp_max");
                aux.main=info.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main");
                aux.description=info.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description");
                aux.picture=info.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon");
                aux.humidity=info.getJSONObject(i).getJSONObject("main").getString("humidity");
                aux.date=info.getJSONObject(i).getString("dt_txt");
                aux.name=response.getJSONObject("city").getString("name");
                days.add(aux);

                //days.add(aux);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return days;


    }

}
