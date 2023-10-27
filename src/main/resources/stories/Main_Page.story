Scenario: 1.Check that the number of runs is correctly displayed in the table
Meta:
!-- @to_run
Given User opens the 'http://localhost:8080/ui/#login' web site
When User logs in with the next data:
| login   | password |
| default | 1q2w3e   |
Then On Main RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab
When On Main RP page in sidebar, user clicks on the 'PROJECTS SELECTOR' tab
And On Main RP page in Project Selector dropdown, user selects 'test_project_for_at_global_mp' project
And On Main RP page, user clicks on the 'LAUNCHES' tab
Then On Main RP page, user should see the table with runs
And On Main RP page, user verifies that the number of rows in the table is equal to value in counter

Scenario: 2.Check that the user can add the new filter for launch
Meta:
!-- @to_run
Given User opens the 'http://localhost:8080/ui/#login' web site
When User logs in with the next data:
| login   | password |
| default | 1q2w3e   |
Then On Main RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab
When On Main RP page in sidebar, user clicks on the 'PROJECTS SELECTOR' tab
And On Main RP page in Project Selector dropdown, user selects 'test_project_for_at_global_mp' project
And On Main RP page, user clicks on the 'LAUNCHES' tab
And On Main RP page, user clicks on the 'Add filter' button
And On Main RP page in 'Launch Name' input, user enters the 'random value' value
And On Main RP page, user clicks on the 'Save' button
And On the 'ADD FILTER' modal, user fills in the name input field with random value and saves it to DataHolder
And On the 'ADD FILTER' modal, user clicks on the 'Add' button
Then On Main RP page on the header, user should see just created filter with name from DataHolder
When On Main RP page on the header, user deletes just created filter

Scenario: 3.Check that the user can add a few new filters for launch
Meta:
@to_run
Given User opens the 'http://localhost:8080/ui/#login' web site
When User logs in with the next data:
| login   | password |
| default | 1q2w3e   |
Then On Main RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab
When On Main RP page in sidebar, user clicks on the 'PROJECTS SELECTOR' tab
And On Main RP page in Project Selector dropdown, user selects 'test_project_for_at_global_mp' project
And On Main RP page, user clicks on the 'LAUNCHES' tab
And On the Main page, user deletes all filters from UI
And User creates '3' new launch filters and saves their names to DataHolder
Then User verifies that newly created and saved to DataHolder filters are all displayed on UI
When User deletes all newly created filters that were saved to DataHolder