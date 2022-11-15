package com.example.demoUrlShortGroovy

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DemoUrlShortGroovyApplication {
    static void main(String[] args) {
        SpringApplication.run(DemoUrlShortGroovyApplication)
        String originalUrl = "https://www.google.co.nz/?gfe_rd=cr&ei=dzbFV&gws_rd=ssl#q=java"
        String encodedUrl = Base64.getUrlEncoder().encodeToString(originalUrl.getBytes())
         println "encode " + encodedUrl
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedUrl);
        String decodedUrl = new String(decodedBytes);
        println "decode " + decodedUrl
        //aHR0cHM6Ly93d3cuZ29vZ2xlLmNvLm56Lz9nZmVfcmQ9Y3ImZWk9ZHpiRlYmZ3dzX3JkPXNzbCNxPWphdmE=





    }

}

