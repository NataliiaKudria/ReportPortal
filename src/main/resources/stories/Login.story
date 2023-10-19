Scenario: 1.Check that the user can log in to ReportPortal
Meta:
Given User opens the 'http://localhost:8080/ui/#login' web site
When User logs in with the next data:
| login      | password |
| default    | 1q2w3e   |
Then On Common RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab

Scenario: 2.Check that the number of runs is correctly displayed in the table
Meta:
@to_run
Given User opens the 'http://localhost:8080/ui/#login' web site
When User logs in with the next data:
| login      | password |
| default    | 1q2w3e   |
Then On Common RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab
When On Common RP page in sidebar, user clicks on the 'PROJECTS SELECTOR' tab
And On Common RP page in Project Selector dropdown, user selects 'test_project_for_at_global_mp' project
And On Common RP page, user clicks on the 'LAUNCHES' tab
Then On Common RP page, user should see the table with runs
And On Common RP page, user verifies that the number of rows in the table is equal to value in counter
