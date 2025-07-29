Feature: functional tests
  Background:
    * url 'http://localhost:8080/v1/api'
    * def payload = read('payload.json')
    * callonce read('setup.feature')
    * def sharedUUID = uuid

  Scenario: Rest API test
    Given path '/send'
    * set payload.sn = sharedUUID
    And request payload
    When method post
    Then status 200
    And match response == payload
    * print 'Response received successfully'

  Scenario: Rest API test with query parameters
    * def MyUtil = Java.type('com.dennis.functionaltestdemo.MyUtil')
    * def queryParams = MyUtil.getGreeting()
    * print queryParams
    * def queryResult = MyUtil.getBookSN(sharedUUID)
    * print queryResult
    * match queryResult == sharedUUID

