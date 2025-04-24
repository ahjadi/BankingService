Feature: multiple account creation
  Scenario: Creating two accounts for a user
    Given A user registers with username "test" and password "Password123"
    Given I have a token for user "test"
    Given I make a POST request to "/accounts/v1/accounts" with body
        """json
          {
            "accountName": "savings",
            "balance": "50000"
          }
        """
    Given I have a token for user "test"
    Given I make a POST request to "/accounts/v1/accounts" with body
        """json
          {
            "accountName": "current",
            "balance": "6500"
          }
        """

     Then the response status code should be 200

Feature: Listing Accounts
  Scenario: As a developer, I can test reading a list of accounts
    Given A user registers with username "test" and password "Password123"
    Given I have a token for user "test"
    Given I make a POST request to "/accounts/v1/accounts" with body
        """json
          {
            "accountName": "savings",
            "balance": "50000"
          }
        """
    Given I have a token for user "test"
    Given I make a POST request to "/accounts/v1/accounts" with body
        """json
          {
            "accountName": "current",
            "balance": "6500"
          }
        """

    When I make a GET request to "/accounts/v1/accounts"
    Then the response body should be
    """json

    ["savings", "current"]

    """

Feature: Closing Account
  Scenario: As a developer, I can test closing a user account
    Given A user registers with username "test" and password "Password123"
    Given I have a token for user "test"
    Given I make a POST request to "/accounts/v1/accounts" with body
        """json
          {
            "accountName": "savings",
            "balance": "50000"
          }
        """


