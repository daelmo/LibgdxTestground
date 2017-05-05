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
* implements stats for a person: walkingSpeed, ...
* implement state machine for actor : dead, unconscious, working, sleeping
* implemented dumb path finding
* implemented dumb task scheduler for person

## TODO

* implement activity KI for working state
* implement activity "walking" with A* path finding
* implement routines (like cooking) using a set of activities
* implement day structure ( sleep time, work time, meal schedule)
* implement passive actors (orphans)
* implement loading of objects from json script
* implement GUI (menu, object placement, ...)
* implement hunger, ...



## First Impressions:
![alt tag](https://raw.githubusercontent.com/daelmo/LibgdxTestground/master/assets/screenshots/17-03-31.jpg)
