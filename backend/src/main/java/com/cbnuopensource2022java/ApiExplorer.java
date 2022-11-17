package com.cbnuopensource2022java;

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

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        String serviceKey = "3OKTaXNqEaQc5eoKYPtmEO0rjIOpOeLVL3slK8F0azOxSsLyVrdT7jGyBNArNLcqyaZxnrK7fTy6XCQM%2FjSbbA%3D%3D";

        StringBuilder urlBuilder = new StringBuilder(
                "http://apis.data.go.kr/B554287/DisabledPersonConvenientFacility/getFacInfoOpenApiJpEvalInfoList");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /* Service Key */
        urlBuilder.append("&" + URLEncoder.encode("wfcltId", "UTF-8") + "="
                + URLEncoder.encode("0000045950", "UTF-8")); /* 데이터 상세 정보 조회를 위한 키값(장애인 편의시설 목록 조회의 시설ID) */

        URL url = new URL(urlBuilder.toString());
        url = getFinalURL(url); // 302 found redirection

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setInstanceFollowRedirects(true);

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;

        rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        /*
         * if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
         * rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         * } else {
         * rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
         * }
         */

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        // save sb to file
        File file = new File("./backend/src/main/java/com/cbnuopensource2022java/data/Data.txt");
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
        fw.write(sb.toString());
        fw.close();

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
}