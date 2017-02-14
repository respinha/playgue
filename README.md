# PlayGue


This is an assignment from the course of Object Oriented Concurrent Programming (5th year of my graduation). In consists in a simulation of a mini-game inspired on [Plague Inc.](http://www.ndemiccreations.com/en/22-plague-inc). 
Its goal was to conceive a simulation that involves different types of interactions between active entities as well as different types of synchronization mechanisms, exploring a structured approach in an object oriented perspective.

The graphical interface was developed using [Gboard](http://sweet.ua.pt/mos/pt.ua.gboard/index.xhtmlinc), a library developed by my [teacher](http://sweet.ua.pt/mos) for academic purposes such as lecturing this course.
In the simulation we can see a map built from a .txt file (currently only a single island map exists) with hypothetical inhabited regions. Each time we click in a square, an Epidemy is launched and it spreads itself throughout the region at each clock tick. When the population detects some infection, it signals the medical team, which then requests the research team for the development of new vaccines.
Each time a square has population infected, its color changes to a new color from a wide set of red colours. When all population deceases in one square, it turns permanently gray. Each time the medicalteam vaccinates all population in one area successfully, it recovers its original colour.

Currently, the user can only lauch 5 epidemy threads at most in order to make it possible for the medical team to have some mathematical chances of winning this race. 
For synchronization, a [concurrent library](http://sweet.ua.pt/mos/pt.ua.concurrent/index.xhtml) besides the one already provided by Java is used (also belonging to the same teacher).
