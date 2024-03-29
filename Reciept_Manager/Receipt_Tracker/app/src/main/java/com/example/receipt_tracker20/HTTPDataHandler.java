package com.example.receipt_tracker20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

class HTTPDataHandler {

    HTTPDataHandler(){
    }

    String GetHTTPData(String requestUrl)
    {
        URL url;
        StringBuilder response = new StringBuilder();
        try
        {
            url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null)
                    response.append(line);
            }
            else
                response = new StringBuilder();
        }
        catch (ProtocolException e) { e.printStackTrace();}
        catch (MalformedURLException e) { e.printStackTrace();}
        catch (IOException e) { e.printStackTrace();}
        return response.toString();
    }
}
