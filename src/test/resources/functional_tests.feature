Feature: functional tests

  Background:
    * url 'http://localhost:8080/v1/api'
    * def payload = read('payload.json')

  Scenario: Rest API test
    Given path '/send'
    And request payload
    When method post
    Then status 200
    And match response == payload
    * print 'Response received successfully'

  Scenario: Rest API test with query parameters
    * def MyUtil = Java.type('com.dennis.functionaltestdemo.MyUtil')
    * def queryParams = MyUtil.getGreeting()
    * print queryParams
    * def queryResult = MyUtil.getBookSN()
    * print queryResult
    * match queryResult == '123'

