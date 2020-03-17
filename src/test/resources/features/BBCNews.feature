Feature: Sign in and Sign out BBC news

Scenario: Verify Sign in and Sign out From BBC news
    Given User BBC News Page
    When User Sign in to BBC news
    Then Verify user signed in successfully
    And Sign out from BBC news
    Then Verify user signed out successfully
