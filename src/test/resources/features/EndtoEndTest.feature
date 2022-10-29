Feature: Login to HRM Application

  @Sanity  @EndtoEndTest
  Scenario: End to End testing

    Given User is on TODO Homepage "QAURL" and verify title
    When User adds multiple todo items in same session
      | Automation Testing   |
      | Automation Testing 1 |
      | Automation Testing 2 |
    Then mark random Todo as completed
    Then edit a random Todo and mark it as completed
    And User adds multiple todo items in same session
      | Automation Testing 3 |
      | Automation Testing 4 |
      | Automation Testing 5 |
    And Show Completed Todo's
    And Show Active Todo's
    And Show All Todo's
    But Delete a random Todo
    Then Mark all Todo as completed and verify items count
    And Clear all completed Todo's
