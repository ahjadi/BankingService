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

        ["test","savings", "current"]

  """
    
    Scenario: closing account
      Given I have a token for user "ahjadi"
      When I make a POST request with no payload to "/accounts/v1/accounts/2b8bc165-a641-48d5-adc0-ce5e5512da7a/close"
      Then the response body should be
  """json

        ["savings", "current"]

  """








