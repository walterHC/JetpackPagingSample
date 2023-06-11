package org.idnp.jetpackpagingsample.tools;

import android.content.Context;

import org.idnp.jetpackpagingsample.entities.Country;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FillDatabase {

    public static List<Country> readAndParseFile(String filename) {
        List<Country> countries = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray jsonArray = new JSONArray(jsonString.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String name_en = jsonObject.getString("name_en");
                String name_es = jsonObject.getString("name_es");
                String continent_en = jsonObject.getString("continent_en");
                String continent_es = jsonObject.getString("continent_es");
                String capital_en = jsonObject.getString("capital_en");
                String capital_es = jsonObject.getString("capital_es");
                String dial_code = jsonObject.getString("dial_code");
                String code_2 = jsonObject.getString("code_2");
                String code_3 = jsonObject.getString("code_3");
                String tld = jsonObject.getString("tld");
                int km2 = jsonObject.getInt("km2");

                Country country = new Country(0, name_en, name_es, continent_en, continent_es, capital_en, capital_es, dial_code, code_2, code_3, tld, km2);
                countries.add(country);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return countries;
    }
}