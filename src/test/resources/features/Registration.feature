Feature: Registration of new users

  Scenario: unregistered user is redirected to login/register page
    Given unregistered user
    When user opens any page
    Then user is redirected to login/register page

  Scenario: unregistered user can register via login/register page
    Given unregistered user
    When user registers with name "user01"
    Then user is registered user



