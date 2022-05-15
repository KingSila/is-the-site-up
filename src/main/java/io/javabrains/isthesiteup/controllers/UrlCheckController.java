package io.javabrains.isthesiteup.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String SITE_UP = "Site is Up";
    private final String SITE_DOWN = "Site is Down";
    private final String INCORRECT_URL = "URL is incorrect";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url) {

        String returnMessage ="";
        try {
            URL urlObject = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) urlObject.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCodeCategory = conn.getResponseCode() / 100;
            System.out.println(responseCodeCategory);
            if(responseCodeCategory !=2 || responseCodeCategory != 3){
                returnMessage = SITE_UP;

            }
            else{
                returnMessage = SITE_DOWN;
            }



        } catch (MalformedURLException e) {
            
            returnMessage = INCORRECT_URL;
            
        } catch (IOException e) {
           
            returnMessage = SITE_DOWN;
        }
        

        return returnMessage;

    }

    
}
