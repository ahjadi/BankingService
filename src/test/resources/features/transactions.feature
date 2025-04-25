Feature: Making transactions

  Scenario: Successful transaction
    Given I have a token for user "ahjadi"
    Then I make a POST request to "/accounts/v1/accounts/transfer" with body
    """json
    {
    "sourceAccount": "2b8bc165-a641-48d5-adc0-ce5e5512da7a",
    "destinationAccount": "3f0dd228-9613-44b1-908c-e3f828b79fa3",
    "transferAmount": 500
    }

    """
    Then the response status code should be 200
    Then the response body should be
    """json
    {
    "newBalanceOfSourceAccount": 2500
    }
    """

#   Scenario: Transferring negative amount
    Given I have a token for user "ahjadi"
    Then I make a POST request to "/accounts/v1/accounts/transfer" with body
    """json
    {
    "sourceAccount": "2b8bc165-a641-48d5-adc0-ce5e5512da7a",
    "destinationAccount": "3f0dd228-9613-44b1-908c-e3f828b79fa3",
    "transferAmount": -1000
    }

    """
    Then the response status code should be 400

#  Scenario: Insufficient funds
#
#    Given I have a token for user "ahjadi"
    Then I make a POST request to "/accounts/v1/accounts/transfer" with body
    """json
    {
    "sourceAccount": "2b8bc165-a641-48d5-adc0-ce5e5512da7a",
    "destinationAccount": "3f0dd228-9613-44b1-908c-e3f828b79fa3",
    "transferAmount": 5000000000
    }

    """
    Then the response status code should be 400


#  Scenario: Transferring to own account
#
#    Given I have a token for user "ahjadi"
    Then I make a POST request to "/accounts/v1/accounts/transfer" with body
    """json
    {
    "sourceAccount": "2b8bc165-a641-48d5-adc0-ce5e5512da7a",
    "destinationAccount": "2b8bc165-a641-48d5-adc0-ce5e5512da7a",
    "transferAmount": 500
    }

    """
    Then the response status code should be 400


#  Scenario: Transferring from a closed account
#
#    Given I have a token for user "ahjadi"
    When I make a POST request with no payload to "/accounts/v1/accounts/2b8bc165-a641-48d5-adc0-ce5e5512da7a/close"
    Then I make a POST request to "/accounts/v1/accounts/transfer" with body
    """json
    {
    "sourceAccount": "2b8bc165-a641-48d5-adc0-ce5e5512da7a",
    "destinationAccount": "3f0dd228-9613-44b1-908c-e3f828b79fa3",
    "transferAmount": 700
    }

    """
    Then the response status code should be 400