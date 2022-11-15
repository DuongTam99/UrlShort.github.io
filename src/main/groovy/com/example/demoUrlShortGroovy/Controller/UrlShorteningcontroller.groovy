package com.example.demoUrlShortGroovy.Controller

import com.example.demoUrlShortGroovy.exception.ValidationError
import com.example.demoUrlShortGroovy.model.Url
import com.example.demoUrlShortGroovy.model.UrlDto
import com.example.demoUrlShortGroovy.model.UrlErrorResponseDto
import com.example.demoUrlShortGroovy.model.UrlResponseDto
import com.example.demoUrlShortGroovy.service.UrlService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.servlet.http.HttpServletResponse
import javax.validation.Valid
import java.time.LocalDateTime
import java.util.regex.Pattern

@RestController
 class UrlShorteningcontroller {

    @Autowired
    private UrlService urlService

    @PostMapping("/generate")
     ResponseEntity<?> generateShortLink(@RequestBody UrlDto urlDto) {
        def responseMap = new HashMap<>()
        Url urlToRet = urlService.generateShortLink(urlDto)
        //Regex
        String regexUrl = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)"
        if(!Pattern.matches(regexUrl,urlDto.getUrl())) {
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto()
            urlErrorResponseDto.setStatus "422"
            urlErrorResponseDto.setError "ERROR, URL khong hop Le, Loi validate"
            return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.BAD_REQUEST)

        }

        if (urlToRet != null) {
            UrlResponseDto urlResponseDto = new UrlResponseDto()
            urlResponseDto.setOriginaUrl urlToRet.getOriginalUrl()
            urlResponseDto.setExpirationDate urlToRet.getExpirationDate()
            urlResponseDto.setShortLink urlToRet.getShortLink()
            responseMap.put "URL", urlResponseDto
            responseMap.put"Status", "1"
            //return new ResponseEntity<>(urlResponseDto, HttpStatus.OK)
            return ResponseEntity.status(200).body(responseMap)
        }
        UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto()
        urlErrorResponseDto.setStatus "404"
        urlErrorResponseDto.setError "ERROR, vio long try again!"
        return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK)

    }

    @RequestMapping(value = "/{shortLink}", method = RequestMethod.GET)
     ResponseEntity<?> redirectToOrignalUrl(@PathVariable String shortLink, HttpServletResponse response) {
        if (StringUtils.isEmpty shortLink) {
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto()
            urlErrorResponseDto.setStatus "404"
            urlErrorResponseDto.setError "ERROR, URL khong hop le"
            return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK)


        }
        Url urlToRet = urlService.getEncodedUrl(shortLink)
        if (urlToRet == null) {
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto()
            urlErrorResponseDto.setStatus "404"
            urlErrorResponseDto.setError "URL, URL khong Ton Tai ,hoac da het han"
            return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK)
        }
        if (urlToRet.getExpirationDate().isBefore(LocalDateTime.now())) {
            urlService.deleteShortLink urlToRet
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto()
            urlErrorResponseDto.setStatus "200"
            urlErrorResponseDto.setError "URL, Ton tai nhung da het han,vua bat dau da xoa url nay"
            return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK)
        }

        response.sendRedirect(urlToRet.getOriginalUrl());

//     HttpHeaders httpHeaders = new HttpHeaders();
//       httpHeaders.setLocation("http://localhost:8080/"+urlToRet.getOriginalUrl());

        return null

    }

}
