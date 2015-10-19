Feature: A user can publish messages to a personal timeline

  Scenario: Reading messages only shows messages for that user
    When Alice posts these messages
      | I love the weather today |
      | Even the rain            |
    And Bob posts these messages
      | Good game though! |
      | Damn! we lost!    |
    Then the messages for Bob are
      | Good game though! (0 seconds ago) |
      | Damn! we lost! (0 seconds ago)    |
    And the messages for Alice are
      | I love the weather today (0 seconds ago) |
      | Even the rain (0 seconds ago)            |

  Scenario: Following a user allows you to see an aggregated view of all the messages
    Given Alice posts these messages
      | I love the weather today |
      | Even the rain            |
    And Bob posts these messages
      | Good game though! |
      | Damn! we lost!    |
    When Bob follows Alice
    Then the wall messages for Bob are
      | Alice - I love the weather today (0 seconds ago) |
      | Alice - Even the rain (0 seconds ago)            |
      | Bob - Good game though! (0 seconds ago)          |
      | Bob - Damn! we lost! (0 seconds ago)             |
