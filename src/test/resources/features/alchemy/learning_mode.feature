Feature: Learning Mode

   Scenario: learning mode gives immediate feedback after submitting an answer
      Given Daniel is in learning mode for the Salesforce Administrator exam
      When Daniel selects the answer A
      And Daniel submits the answer
      Then Daniel should see whether the answer was correct or incorrect
      And Daniel should see the explanation for the question
      And Daniel should be able to move to the next question

   Scenario: learning mode does not show feedback before submitting
      Given Daniel is in learning mode for the Salesforce Administrator exam
      When Daniel selects the answer A
      Then Daniel should not see whether the answer was correct or incorrect

   Scenario: back button takes you back to the previous question
      Given Daniel is in learning mode for the Salesforce Administrator exam
      When Daniel selects the answer A
      And Daniel submits the answer
      Then Daniel should see whether the answer was correct or incorrect
      And Daniel should be able to move to the next question
      And Daniel should be able to go back to the previous question