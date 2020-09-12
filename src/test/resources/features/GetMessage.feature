Feature: Get a message describing when trash pick up is
  Scenario: User successfully gets a message
    Given a valid api gateway request
    When a user requests a message
    Then a valid message about when trash pickup is returned