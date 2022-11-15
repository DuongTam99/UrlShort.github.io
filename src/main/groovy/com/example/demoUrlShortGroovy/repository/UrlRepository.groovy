package com.example.demoUrlShortGroovy.repository

import com.example.demoUrlShortGroovy.model.Url
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
 interface UrlRepository extends MongoRepository<Url,Long> {
     Url findByShortLink(String shortLink)

}

