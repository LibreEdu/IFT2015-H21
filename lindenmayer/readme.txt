Here are the structures we used:
- For the first part, we have a min heap, in order to store the living males. We
  can remove the next sim that will die and choose a random sim for mating. For 
  this, we have our own structure, a generic heap that uses the default 
  comparator of the elements of the heap, sims in our case, and a comparator 
  that compares the death dates.
- The second structure used is a PriorityQueue to store the events.

For the second part, the coalescence, we used a PriorityQueue to store the final
population which is used to initialize the coalescence process. This 
PriorityQueue was initialized from the death events at the end of the 
simulation. Knowing that the death events are generated during the birth event,
they are a reflection of the living population at a time t. This allowed us to
simulate the first part without having to manage the population of female sims.

For the first part, the heap is a min heap, we want the smallest date (of 
death), while for the second part, it is a max heap, we want the biggest date 
(of birth), in order to have the youngest sim, according to the instructions.

The structures were initialized with default sizes that depend on the size of
the initial population. To find the ratio, we looked at the size of the 
structure when the program runs and took the maximum size over 10 trials.

To launch the simulation, just do :
java -jar pedigree.jar

We can specify six parameters :
- the population
- the duration of the simulation in years
- the accident rate per year
- the mortality rate increasing with age
- the maximum age

The default values of these values are :
- population: 1,000
- duration of the simulation in years: 10,000
- accident rate per year: 0.01 (probability of dying each year)
- mortality rate increasing with age : 12.5 (the mortality rate doubles every 8 
  years)
- maximum age: 100

For the output, we have for the first part an output in the form :
Year Population

Then for the second part, the output is in the form :
Year Forfathers
then :
Year Formothers
 