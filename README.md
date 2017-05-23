# LibgdxTestground
These are the first cornerstones of a 2D game similar to the simulation-based games Prison Architect or Rimworld. 
For the implementation using Java and the game engine libGDX.

## Implemented Features
* level generation
* zooming in and out within a limited range
* camera shift with arrow keys
* random character synthesis for 4 viewpoints
* object placement on level grit
* time is passing and can be paused with space key
* grit for traversability is implemented
* settings are loaded from a properties file
* implements stats for a person: walkingSpeed, hunger and hungerRate
* implement state machine for actor : dead, unconscious, working, sleeping
* implemented straight path finding
* implemented dumb task scheduler for an person
* loading names for characters from JSON-file, assigning one randomly
* walking activity finaly implemented, using different view directions for walking
* daylight cycle implemented, it gets dark during the night

## TODO

* setting borders for scrolling of screen
* drawing images for different growth of person
* implement activity KI for working state
* implement activity "walking" with A* path finding
* implement routines (like cooking) using a set of activities
* implement day structure ( sleep time, work time, meal schedule)
* implement passive actors (orphans)
* implement loading of objects from json script
* implement GUI (menu, object placement, ...)



## First Impressions:
![alt tag](https://raw.githubusercontent.com/daelmo/LibgdxTestground/master/assets/screenshots/17-05-16.jpg)
