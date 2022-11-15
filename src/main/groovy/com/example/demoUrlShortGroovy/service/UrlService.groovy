package com.example.demoUrlShortGroovy.service

import com.example.demoUrlShortGroovy.model.Url
import com.example.demoUrlShortGroovy.model.UrlDto
import org.springframework.stereotype.Service

@Service
 interface UrlService {
 Url generateShortLink(UrlDto urlDto)
 Url persistShortLink(Url url)
 Url getEncodedUrl(String url)
 void deleteShortLink(Url url)
}



