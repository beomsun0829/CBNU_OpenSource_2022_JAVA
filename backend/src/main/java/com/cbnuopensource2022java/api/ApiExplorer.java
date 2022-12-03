package com.cbnuopensource2022java.api;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.cbnuopensource2022java.repository.LocationRepository;

public class ApiExplorer {
    static String ServiceKey = "3OKTaXNqEaQc5eoKYPtmEO0rjIOpOeLVL3slK8F0azOxSsLyVrdT7jGyBNArNLcqyaZxnrK7fTy6XCQM%2FjSbbA%3D%3D";
    static String MainURL_FaclList = "http://apis.data.go.kr/B554287/DisabledPersonConvenientFacility/getDisConvFaclList";
    static String MainURL_UtilList = "http://apis.data.go.kr/B554287/DisabledPersonConvenientFacility/getFacInfoOpenApiJpEvalInfoList";
    static String MaxNumOfRows = "1000";

    public static JSONObject Explorer(String[] args, StringBuilder urlBuilder) throws IOException {
        URL url = new URL(urlBuilder.toString());
        url = getFinalURL(url); // 302 found redirection

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setInstanceFollowRedirects(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        JSONObject JSONobj = XML.toJSONObject(sb.toString());
        return JSONobj;

        // save sb to file
        // saveToFile(sb.toString(),
        // "./backend/src/main/java/com/cbnuopensource2022java/data/Data.txt");
    }

    public static String getLocation(String page) throws IOException {
        StringBuilder U = new StringBuilder(MainURL_FaclList);
        U.append("?" + "serviceKey=" + ServiceKey);
        U.append("&" + "numOfRows=" + MaxNumOfRows);
        U.append("&" + "pageNo=" + page);

        JSONObject JSONobj = Explorer(null, U);
        JSONArray JSONarr = rmUselessLocationData(JSONobj);
        return JSONarr.toString();
    }

    public static String getLocationByName(String name) throws IOException {
        StringBuilder U = new StringBuilder(MainURL_FaclList);
        U.append("?" + "serviceKey=" + ServiceKey);
        U.append("&" + "faclNm=" + URLEncoder.encode(name, "UTF-8"));

        JSONObject JSONobj = Explorer(null, U);
        JSONArray JSONarr = rmUselessLocationData(JSONobj);
        return JSONarr.toString();
    }

    public static String getLocations() throws IOException {
        String result = "";
        for (int i = 1; i <= 145; i++) {
            result += getLocation(Integer.toString(i));
        }
        return result;
    }

    public static String getUtilById(String id) throws IOException {
        while (id.length() < 10)
            id = '0' + id;

        StringBuilder U = new StringBuilder(MainURL_UtilList);
        U.append("?" + "serviceKey=" + ServiceKey);
        U.append("&" + "wfcltId=" + id); // 관리시설 id
        JSONObject JSONobj = Explorer(null, U);
        JSONobj = rmUselessUtilData(JSONobj);
        return JSONobj.toString();
    }

    public static String initDB(LocationRepository locationRepository) throws JSONException, IOException {
        locationRepository.deleteAll();

        for (int i = 1; i <= 145; i++) {
            JSONArray JSONarr = new JSONArray(getLocation(Integer.toString(i)));
            System.out.println(JSONarr.toString());

            for (int j = 0; j < 1000; j++) {
                try {
                    JSONObject item = JSONarr.getJSONObject(j);
                    System.out.println("Saving :" + item.getString("wfcltId"));
                    System.out.println("Saving :" + item.getString("faclNm"));
                    System.out.println("Saving :" + item.getString("faclTyCd"));
                    System.out.println("Saving :" + item.getString("lcMnad"));
                    System.out.println("Saving :" + item.getString("salStaDivCd"));
                    System.out.println("Saving :" + String.valueOf(item.getFloat("faclLat")));
                    System.out.println("Saving :" + String.valueOf(item.getFloat("faclLng")));

                    locationRepository.save(new com.cbnuopensource2022java.entity.Location(
                            Integer.parseInt(item.getString("wfcltId")),
                            item.getString("faclNm"),
                            item.getString("faclTyCd"),
                            item.getString("lcMnad"),
                            item.getString("salStaDivCd"),
                            String.valueOf(item.getFloat("faclLat")),
                            String.valueOf(item.getFloat("faclLng"))));
                } catch (Exception e) {
                    System.out.println("page : " + i + "Skip" + j + "th item");
                }
            }
        }
        return "";
    }

    public static JSONArray rmUselessLocationData(JSONObject JSONobj) {
        System.out.println(JSONobj.toString());
        return JSONobj.getJSONObject("facInfoList").getJSONArray("servList");
    }

    public static JSONObject rmUselessUtilData(JSONObject JSONobj) {
        System.out.println(JSONobj.toString());
        return JSONobj.getJSONObject("facInfoList").getJSONObject("servList");
    }

    public static String xmlToJson(String xml) throws JSONException {
        JSONObject JsonObj = XML.toJSONObject(xml);
        return JsonObj.toString(4);
    }

    public static URL getFinalURL(URL url) {
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setInstanceFollowRedirects(false);
            con.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            // con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            con.addRequestProperty("Referer", "https://www.google.com/");
            con.connect();
            // con.getInputStream();
            int resCode = con.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_SEE_OTHER
                    || resCode == HttpURLConnection.HTTP_MOVED_PERM
                    || resCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                String Location = con.getHeaderField("Location");
                if (Location.startsWith("/")) {
                    Location = url.getProtocol() + "://" + url.getHost() + Location;
                }
                return getFinalURL(new URL(Location));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return url;
    }

    public static void saveToFile(String data, String path) throws IOException {
        File file = new File(path);
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
        fw.write(data);
        fw.close();
    }
}

/*
 * if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
 * rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
 * } else {
 * rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
 * }
 */