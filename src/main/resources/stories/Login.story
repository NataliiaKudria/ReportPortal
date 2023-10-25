Scenario: 1.Check that the user can log in to ReportPortal
Meta:
!-- @to_run
Given User opens the 'http://localhost:8080/ui/#login' web site
When User logs in with the next data:
| login   | password |
| default | 1q2w3e   |
Then On Main RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab
