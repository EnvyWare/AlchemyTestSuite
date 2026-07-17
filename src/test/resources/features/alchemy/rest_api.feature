@restassured
Feature: Alchemy REST API page

    Scenario: Sending an OPTIONS request to the base-url should return response code 200 with no body
        When I check the end point is active
        Then the response should give code 204