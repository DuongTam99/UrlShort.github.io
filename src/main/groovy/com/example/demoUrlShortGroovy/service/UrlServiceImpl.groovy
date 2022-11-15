package com.example.demoUrlShortGroovy.service

import com.example.demoUrlShortGroovy.model.Url
import com.example.demoUrlShortGroovy.model.UrlDto
import com.example.demoUrlShortGroovy.repository.UrlRepository
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.LocalDateTime

@Component
class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository urlRepository

    @Override
    Url generateShortLink(UrlDto urlDto) {
        //Neu UrlDto co ton tai,xac nhan la da co duoc link dai
        if (StringUtils.isNotEmpty urlDto.getUrl()) {
            String encodeUrl = encodeUrl(urlDto.getUrl())
            Url urlToPersist = new Url()
            urlToPersist.setCreationDate LocalDateTime.now()
            urlToPersist.setOriginalUrl urlDto.getUrl()
            urlToPersist.setShortLink encodeUrl
            urlToPersist.setExpirationDate getExpirationDate (urlDto.getExpirationDate(), urlToPersist.getCreationDate())
            Url urlToRet = persistShortLink(urlToPersist)
            if (urlToRet != null) {
                return urlToRet
            }
            return null
        }
        return null
    }

    private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {
        if (StringUtils.isBlank expirationDate) {
            return creationDate.plusSeconds(300)
        }
        LocalDateTime expirationDateToRet = LocalDateTime.parse expirationDate
        return expirationDateToRet

    }

    private String encodeUrl(String url) {
        def encodedUrl = ""
        def possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        for (def i = 0; i < 5; i++) {
            encodedUrl += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()))
        }
        return encodedUrl
    }

    @Override
    Url persistShortLink(Url url) {
        Url urlToRet = urlRepository.save url
        return urlToRet
    }

    @Override
    Url getEncodedUrl(String url) {
        Url urlToRet = urlRepository.findByShortLink url
        return urlToRet
    }

    @Override
    void deleteShortLink(Url url) {
        urlRepository.delete url


    }
}
