# My Personal Project - A Course Registration System

## Project Introduction

**This application simulates a school's course registration functionality.** It should come with all the essentials of a 
course registration system, including but not limited to students being able to add/drop/their courses, and students 
being able to create their own account with its own associated course registrations.

I picked this project because of how often students complain about UBC's SSC portal. I wanted to see how hard a similar
portal would be to code (although I acknowledge that this project is very barebones!). 

# User Stories

- As a user, I want to be able to add multiple courses to my course list (and be notified if the course couldn't be 
added).
- As a user, I want to see the list of available courses before adding one.
- As a user, I want to be able to remove multiple courses from my course list (and be notified if the course couldn't
be dropped).
- As a user, I want to see the list of courses I'm enrolled in before removing one. 
- As a user, I want to be able to view the courses I'm enrolled in.
- As a user, I want to have a dedicated account with a **unique** username and password, which my course list 
is associated with. 
- As a user, I want to be able to log into and out of my account when desired.
- As a user, I want my account's details (specifically, my registered courses, username and password) to be saved 
whenever I make changes to them. 
- As a user, I want to be able to log into and out of my account whenever I want, even after closing and 
reopening the application. 

**Phase 4: Task 2**

Made CourseList robust by making the unenroll method potentially throw a checked CourseNotEnrolledException.

**Phase 4: Task 3**

- Refactor most of the UI classes to fix duplication; abstract out their individual method details. 
- Move dependencies from method code to explicit fields to make code clearer.
- Remove semantic coupling in setting color of the 'BACK' button in the UI. Right now, the BACK button's color is set to yellow
in multiple unique places, should ideally be abstracted to a single method which sets the button's color to yellow.
- Make all ArrayLists of Course into CourseLists 
- Handle exceptions via checked exceptions instead of if-then clauses in the Student class