package com.s3.demo.s3study

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.s3.demo.s3study.controller", "com.s3.demo.s3study.service"])
class S3StudyApplication {

  @Value('${aws.s3.secret_access_key}')
  String secretKey

  @Value('${aws.s3.region}')
  String region


  @Value('${aws.s3.access_key_id}')
  String accessKey

  static void main(String[] args) {
    SpringApplication.run(S3StudyApplication, args)
  }

  @Bean
  AmazonS3 getS3Client() {
    BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey)
    return AmazonS3ClientBuilder.standard()
        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://s3.amazonaws.com", region))
        .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
        .build()
  }


}

