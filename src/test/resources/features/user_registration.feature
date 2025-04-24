Feature: user registration

  Scenario: successful user registration
    When I make a POST request to "/public/users/v1/register" with body
  """
  {
    "username": "ahjadi",
    "password": "Password123"
  }
  """
    Then the response status code should be 200
