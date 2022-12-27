Feature: Google Maps API

@AddPlace
Scenario Outline: Add a Place successfully
Given Add place payload with "<name>" "<address>" "<phone>"
When user calls "AddPlace" with "post" request
Then API call is succesful with 200 status code
And "status" in Response body is "OK"
And "scope" in Response body is "APP"
And verify "place_id" of "name" "<name>" using "GetPlace" with "get" method

Examples:
|name | address | phone | 
|Sangavi|B 48 , MMDA , Chennai | 9943728935 |
|Johny | 599 , TV puram , Ponneri | 8754860870|  

@DeletePlace
Scenario: Delete place 
Given Delete place Payload
When user calls "DeletePlace" with "post" request
And "status" in Response body is "OK"
