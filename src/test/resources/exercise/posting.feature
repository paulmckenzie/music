Feature: A user can publish messages to a personal timeline

  Scenario: Reading messages only shows messages for that user
    When Alice posts these messages
      | I love the weather today |
      | Even the rain            |
    And Bob posts these messages
      | Good game though! |
      | Damn! we lost!    |
    Then the messages for Bob are
      | Good game though! |
      | Damn! we lost!    |
    And the messages for Alice are
      | I love the weather today |
      | Even the rain            |
