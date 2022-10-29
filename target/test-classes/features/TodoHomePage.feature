Feature: Login to HRM Application

  Background:
    Given User is on TODO Homepage "QAURL" and verify title

  @Sanity  @Add
  Scenario: Add a Todo

    When User enters todo "Automation Testing"
    And Verify count

  @Sanity
  Scenario: Add a Todo and delete it

    When User enters todo "Automation Testing"
    Then Delete it
    And Verify count

    @Sanity
  Scenario: Add a Todo and Edit it

    When User enters todo "Automation Testing"
    Then Edit First Todo with random Text
      And Verify count

   @Add
  Scenario: Add multiple Todos

    When User adds multiple todo items in same session
      | Automation Testing   |
      | Automation Testing 1 |
      | Automation Testing 2 |
     And Verify count

  @Add @Smoke
  Scenario: Add multiple Todos and Complete & Clear all Todo

    When User adds multiple todo items in same session
      | Automation Testing   |
      | Automation Testing 1 |
      | Automation Testing 2 |
    Then Mark all Todo as completed and verify items count
    And Clear all completed Todo's
    And Verify count

  @Sanity @Add
  Scenario: Add Todos and Complete added Todo's and Add again

    When User adds multiple todo items in same session
      | Automation Testing   |
      | Automation Testing 1 |
      | Automation Testing 2 |
    Then Mark all Todo as completed and verify items count
    And User adds multiple todo items in same session
      | Automation Testing 3 |
      | Automation Testing 4 |
      | Automation Testing 5 |
    And Verify count

  @Sanity @Smoke
  Scenario: Show active Todo's from the list

    When User adds multiple todo items in same session
      | Automation Testing   |
      | Automation Testing 1 |
      | Automation Testing 2 |
    Then Mark all Todo as completed and verify items count
    And User adds multiple todo items in same session
      | Automation Testing 3 |
      | Automation Testing 4 |
      | Automation Testing 5 |
    Then Show Active Todo's
    And Verify count

  @Sanity
  Scenario: Show Completed Todo's from the list

    When User adds multiple todo items in same session
      | Automation Testing   |
      | Automation Testing 1 |
      | Automation Testing 2 |
    Then Mark all Todo as completed and verify items count
    And User adds multiple todo items in same session
      | Automation Testing 3 |
      | Automation Testing 4 |
      | Automation Testing 5 |
    Then Show Completed Todo's
    And Verify count

  @Add @Smoke
  Scenario: Show All Todo's from the list

    When User adds multiple todo items in same session
      | Automation Testing   |
      | Automation Testing 1 |
      | Automation Testing 2 |
    Then Mark all Todo as completed and verify items count
    And User adds multiple todo items in same session
      | Automation Testing 3 |
      | Automation Testing 4 |
      | Automation Testing 5 |
    Then Show Completed Todo's
    And  Show All Todo's
    And Verify count

  @Add @Smoke
  Scenario: Add multiple Todo's and Mark random Todo as completed

    When User adds multiple todo items in same session
      | Automation Testing   |
      | Automation Testing 1 |
      | Automation Testing 2 |
      | Automation Testing 3 |
      | Automation Testing 4 |
      | Automation Testing 5 |
      | Automation Testing 3 |
      | Automation Testing 4 |
      | Automation Testing 5 |
    Then mark random Todo as completed
    And Verify count

    @Add @Smoke
  Scenario: Add multiple Todo's, Edit a random Todo and mark it as completed

    When User adds multiple todo items in same session
      | Automation Testing   |
      | Automation Testing 1 |
      | Automation Testing 2 |
      | Automation Testing 3 |
      | Automation Testing 4 |
      | Automation Testing 5 |
      | Automation Testing 3 |
      | Automation Testing 4 |
      | Automation Testing 5 |
    Then edit a random Todo and mark it as completed
      And Verify count


