package com.s3.demo.s3study.service


import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.S3Object
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
@Slf4j
class S3Service {


  @Autowired
  AmazonS3 amazonS3

  @Value('${aws.s3.bucket}')
  String bucket


  String getFileContent(String filename) {
    S3Object s3object =  amazonS3.getObject(bucket, filename+".txt")
    return s3object.getObjectContent().getText("UTF-8")
  }

  String updateFileContent(String filename, Map requestMap) {
    File textFile = createFile(requestMap)
    PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, filename + ".txt", textFile)
    putObjectRequest.redirectLocation = 'http://' // added this value for internal redirection
    amazonS3.putObject(putObjectRequest)
    log.info("File uploaded successfully")
    return "Success"
  }

  private File createFile(Map requestMap) {
    File textFile = File.createTempFile("temp", ".txt")
    FileWriter writer = new FileWriter(textFile)
    writer.write(requestMap.toString())
    writer.close()
    textFile
  }


}
