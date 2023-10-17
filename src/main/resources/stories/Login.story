Meta:
Narrative:
As a user
I want to log in to ReportPortal website
So that I can get the full picture of the runs

Scenario: scenario description
Given User opens the 'http://localhost:8080/ui/#login' web site
When User logs in with the next data:
| login      | password |
| default    | 1q2w3e   |
And On Common RP page, user clicks on the 'LAUNCHES' tab
And On Common RP page, user clicks on the 'FILTERS' tab
!-- And User closes the current browser window
