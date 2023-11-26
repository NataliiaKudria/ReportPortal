Scenario: 3.Check the Rest endpoint.
Meta:
@to_run
Given User opens the 'https://rp.epam.com/ui/#login' web site
When User logs in with the next data:
| login               | password |
| DefaultPersonalUser | 1q2w3e4r |
And User navigates back
And On Log In page, user clicks on the 'Login' button
When via Rest user gets all the launches