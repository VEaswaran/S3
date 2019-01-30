package com.s3.demo.s3study.controller

import com.s3.demo.s3study.service.S3Service
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = "/s3")
@Slf4j
class ControllerS3 {

  @Autowired
  S3Service s3Service

  @RequestMapping(path = "/get/{filename}", method = RequestMethod.GET)
  String getFileContent(@PathVariable final String filename) {
    String response = s3Service.getFileContent(filename)
    log.info("request processed successfully")
    return response
  }

  @RequestMapping(path = "/post/{filename}", method = RequestMethod.POST, consumes = "application/json")
  String updateFileContent(@PathVariable final String filename, @RequestBody Map requestMap) {
    s3Service.updateFileContent(filename, requestMap)
    log.info("request information updated processed successfully")
    return "Success"
  }


}
