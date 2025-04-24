Feature: jwt token generation

  Scenario: As a user, I can sign in to my account.

    When I make a POST request to "/auth/login" with body
    """
{
  "username": "test1",
  "password": "Test12345"
}
    """
    Then the response status code should be 200
    Then a token exists