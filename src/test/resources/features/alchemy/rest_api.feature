@restassured
Feature: Alchemy REST API page

    Scenario: Sending an OPTIONS request to the base-url should return response code 200 with no body
        When I check the end point is active
        Then the response should give code 204

    Scenario: Checking the platforms end point should return a list that contains salesforce
        When I get the platforms
        Then the response should give code 200
        And there should be a list of minimum size 1
        And it should contain the Salesforce platform

    Scenario: Checking the salesforce exams end point should return a list of size 1 that contains the platform admin exam
        When I get the exams for Salesforce
        Then the response should give code 200
        And there should be a list of minimum size 1
        And it should contain the Administrator exam

    Scenario: Checking the GCP exams end point should return nothing
        When I get the exams for Google
        Then the response should give code 200
        And there should be a list of size 0

    Scenario: Checking the exams end point should return code 400 when missing the platform parameter
        When I send an invalid request to the exams end point
        Then the response should give code 400
        And I get the error "Missing 'platform' query parameter (e.g., ?platform=AWS)"

    Scenario: Checking the questions end point should return code 400 when missing the exam parameter
        When I send an invalid request to the questions end point
        Then the response should give code 400
        And I get the error "Missing 'exam' query parameter (e.g., ?exam=SF-ADMIN)"