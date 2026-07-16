@pageobjects @screenplay
Feature: Alchemy web page

   Scenario: loading the initial web page
      Given Daniel is doing exam practice
      Then Daniel should see the home page

   Scenario: clicking into salesforce platform
      Given Daniel is doing exam practice
      When Daniel clicks the Salesforce platform
      Then Daniel should see exams appear

   Scenario: clicking into google platform
      Given Daniel is doing exam practice
      When Daniel clicks the Google platform
      Then Daniel should not see exams appear
