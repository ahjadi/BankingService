Feature: KYC Creation, Update and View

  Scenario: User submits KYC information and updates
    Given A user registers with username "test" and password "Password123"
    Given I have a token for user "test"
    When I make a POST request to "/users/v1/kyc" with body
    """json
    {
      "firstName": "Ali",
      "lastName": "Aljadi",
      "dateOfBirth": "1998-04-14",
      "nationality": "Kuwaiti",
      "salary": 75000
    }
    """
    Then the response status code should be 200
    And the response body should be
    """json
    {
      "firstName": "Ali",
      "lastName": "Aljadi",
      "dateOfBirth": "1998-04-14T00:00:00.000+00:00",
      "salary": 75000
    }
    """

    When I make a POST request to "/users/v1/kyc" with body
    """json
    {
      "firstName": "Ali",
      "lastName": "Aljadi",
      "dateOfBirth": "2004-04-14",
      "nationality": "KWT",
      "salary": 4444
    }
    """
    Then the response status code should be 200
    And the response body should be
    """json
    {
      "firstName": "Ali",
      "lastName": "Aljadi",
      "dateOfBirth": "2004-04-14T00:00:00.000+00:00",
      "salary": 4444
    }
    """
    When I make a GET request to "/users/v1/kyc"
    Then the response status code should be 200
    And the response body should be
    """json
    {
      "firstName": "Ali",
      "lastName": "Aljadi",
      "dateOfBirth": "2004-04-14",
      "salary": 4444
    }
    """
