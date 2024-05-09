# My Personal Project

## Phase 0: Proposal

A tower building game with a variety of different floors. 
The user builds a mini city within one tower! Each floor 
must have a different purpose meaning there would be a class 
of floor but also multiple subclasses of floors such as: 

- Residence
- Recreation 
- Restaurant 
- Retail

Floor would be a non-trivial 
class (X) because it would have different types, and thus 
different characteristics associated with them, and the tower 
itself would be a non-trivial class (Y) because it would hold 
the different floors in order. Each floor would have a name, floor number, 
level, revenue, and possibly a capacity and each subtype of floor would have extra 
characteristics based on their type. The user would be able to build floors using 
an in game currency which is earned from the revenue of different floors in different ways, 
for example from residence floors, money would be earned in the 
form of rent etc. The user would attempt to maintain an equal balance 
of population (residence floors) and activities, which could be done 
easily with a simple math equation based on the number of residents in 
the tower vs, the capacity of each of the non-residence floors. 

I'm passionate about this idea because I grew up playing a game similar to this and I think that
while the premise is vry simple, the game is very fun and would fit the requirements of this 
project very well. This game can be played very easily, and it can be saved to memory so that a user 
can reopen their progress with their tower.

#### User Stories:
- As a user, I want to be able to add new floors to my tower
- As a user, I want to be able to view a list of floors in my tower
- As a user, I want to be able to get any characteristic of the floor based on its level
- As a user, I want to set the type of the floors that I'm adding to the tower

#### User Stories Phase 2:
- As a user, I want to be able to save my current tower, including the current floors and occupants
- As a user, I want to be able to reopen a saved tower and continue playing the game

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by entering a name 
for your floor into the first box, entering a valid floor type (Residential, Commercial, or Recreational) and then
click the button labelled "Add Floor"
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking the 
"Type Dist" button to view a type distribution of the floors currently in the tower
- You can locate my visual component by opening the application
- You can save the state of my application by clicking the "Save Tower" button
- You can reload the state of my application by clicking the "Load Tower" button

### Phase 4: Task 2

Sun Apr 07 15:21:22 PDT 2024 
Added residential floor: Apartments\
Sun Apr 07 15:21:29 PDT 2024
Added commercial floor: Sushi\
Sun Apr 07 15:21:36 PDT 2024
Added recreation floor: Gym\
Sun Apr 07 15:21:39 PDT 2024
Added recreation floor: Yoga\\\

### Phase 4: Task 3

Based on my UML Class Diagram I am quite happy with the design aspects of the application itself. 
There seems to be decently low coupling which I'm quite happy about, and there seems to be somewhat
high cohesion which I'm also pretty happy about. If I could refactor, however, I would love to go back and 
remove the different floor types as their own class. In the beginning I believed that it would be something that
I would use to allow for different functions for each floor type, however my project never became that in depth, and 
instead it just made the addition of floors slightly tricky especially during the persistence stage. 