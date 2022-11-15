package com.example.demoUrlShortGroovy.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import java.time.LocalDateTime

@Document
 class Url {
    @Id
     String id
     String originalUrl
     String shortLink
     LocalDateTime creationDate
     LocalDateTime expirationDate


}
