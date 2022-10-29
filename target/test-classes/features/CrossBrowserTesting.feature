Feature: Login to HRM Application

  @Add @Smoke
  Scenario Outline: Test Scenarios across browsers

    Given User navigates to TODO Homepage "QAURL" using "<Browser>" and verify title
    When User enters todo "<ToDo>"

    Examples:
      | Browser | ToDo    |
      | Chrome  | Chrome  |
      | FireFox | FireFox |
#     | Safari  | Safari  | (Requires admin access to enable safari before triggering This)
#     | Edge  | Edge  | (Requires admin access to enable Edge before triggering This)
