Feature: A user can publish messages to a personal timeline

  Scenario: Reading messages only shows messages for that user
    Given the time is 10:00:00
    When Alice posts these messages
      | I love the weather today | 10:00:10 |
      | Even the rain            | 10:00:30 |
    And Bob posts these messages
      | Damn! we lost!    | 10:01:20 |
      | Good game though! | 10:02:30 |
    And the time is 10:03:10
    Then the messages for Bob are
      | Good game though! (40 seconds ago) |
      | Damn! we lost! (1 minute ago)      |
    And the messages for Alice are
      | Even the rain (2 minutes ago)            |
      | I love the weather today (3 minutes ago) |

  Scenario: Following a user allows you to see an aggregated view of all the messages
    Given the time is 10:00:00
    And Alice posts these messages
      | I love the weather today | 10:00:10 |
      | Even the rain            | 10:00:30 |
    And Bob posts these messages
      | Damn! we lost!    | 10:01:20 |
      | Good game though! | 10:02:30 |
    When the time is 10:03:10
    And Bob follows Alice
    Then the wall messages for Bob are
      | Bob - Good game though! (40 seconds ago)         |
      | Bob - Damn! we lost! (1 minute ago)              |
      | Alice - Even the rain (2 minutes ago)            |
      | Alice - I love the weather today (3 minutes ago) |
