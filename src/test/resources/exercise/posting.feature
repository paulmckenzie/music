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

  Scenario: Following a user allows you to see an aggregated view of all the messages
    Given Alice posts these messages
      | I love the weather today |
      | Even the rain            |
    And Bob posts these messages
      | Good game though! |
      | Damn! we lost!    |
    When Bob follows Alice
    Then the wall messages for Bob are
      | I love the weather today |
      | Even the rain            |
      | Good game though!        |
      | Damn! we lost!           |
