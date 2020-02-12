# new feature
# Tags: optional


Feature: Validate dummy rest Api

  @AddPlace
  Scenario Outline: Validate new Employee is being successfully added
    Given App Employee Payload with "<name>" "<salary>" "<age>"
    When User calls "addPlaceAPI" with "POST" http request
    Then Api call got "success" with "200" response
    And "status" in response body is "OK"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name  | salary | age |
      | User1 | 1000   | 30  |
 #     |User2 |  2000  |31 |

  @DeletePlace
  Scenario: Verify if Delete Place functionality is working

    Given DeletePlace Payload
    When User calls "deletePlaceAPI" with "POST" http request
    Then Api call got "success" with "200" response
    And "status" in response body is "OK"


 # Scenario: Verify if update Employee functionality is working

    #Given Update Employee Payload "Employee"
    #When User calls "updateEmployeeAPI" with "PUT" http request
    #Then Api call got "success" with "200" response
    #And "status" in response body is "success"

