Feature: Starting quiz modes

   Scenario: starting a mock quiz
      Given Daniel has selected the Salesforce platform
      And the Salesforce Administrator exam is available
      When Daniel starts a mock quiz for the Salesforce Administrator exam
      Then Daniel should see the mock quiz screen
      And Daniel should see a countdown timer
      And Daniel should see the first question
      And Daniel should not see answer feedback before finishing the quiz

   Scenario: starting learning mode
      Given Daniel has selected the Salesforce platform
      And the Salesforce Administrator exam is available
      When Daniel starts learning mode for the Salesforce Administrator exam
      Then Daniel should see the learning mode quiz screen
      And Daniel should see a timer counting up
      And Daniel should see the first question
      And Daniel should see a Submit Answer button

  Scenario: starting category learning mode
      Given Daniel has selected the Salesforce platform
      And the Salesforce Administrator exam is available
      When Daniel chooses the Automation category
      And Daniel starts category learning mode
      Then Daniel should see the learning mode quiz screen
      And the question category should be Automation