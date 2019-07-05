## The Dependancy graph

```
We can model a job structure as a directed dependancy graph. 
Each job in our system can be modelled as a node. Each dependancy between two jobs can be modelled as a directed edge from one job to another. We call the job a that depends on another job b as the child of b. The dependancy edge can either be from parent->child(b->a) or child->parent(a->b). Since parent->child edges are more intuitive, we select that format.

Lets take an example from the given challenge

Job structure:

a =>
b => c
c => f
d => a
e => b
f =>

This job structure is represented as the following dependancy graph


```
![](graph.png)
```
Each path on the graph represents a dependancy sequence. We thus need to traverse all paths and maintain a global dependancy sequence.
The algorithm to do this is as follows:
- We initialize an empty stack to store the job sequence
- Select a random job node as a path start point. It can be any node that hasnt been traversed so far.
- Recursive traverse its children in a depth-first search manner, marking each one as visited along the way.
- Once a job with no children has reached, we have reached the end of the current path. We add this job to our stack and backtrack.
- Once a job with all children who have been visited is encountered, we add that to the stack.
- We do this for all jobs in our list of jobs.

A dry run of this algorithm for our earlier example is shown as follows:

```
![](graph.png)

```
Now consider the following job structure:

a =>
b => c
c => f
d => a
e =>
f => b

The graph for this structure is as follows:
```
![](graph.png)
```
We can see that there is a cycle in our graph. This would cause our algorithm to fail since we wont be able to reach a terminal node. One way to check for a cycle is to see if the child node is already visited before. However, since we are randomly selecting start points, a child node being visited before could also mean that it was part of a previous traversed path as can be seen in the dry run example above when d is selected as start point.

To circumvent this ambiguity, we use another temporary visited marker that marks nodes visited only during a particular path. Once the path is terminated, we unmark all temporary visited markers. This allows us to check for visited nodes during traversal of path that belong to the path. If we encounter a temporarily visited node in our path traversal, there must be a cycle.
```
```
Additional things about the solution structure.
- Since the graph is modelled using adjacency lists, we refrain from using visited and temp-visited arrays, since we would then have to map each job to an index in the array, which is extra work. Instead we provide each job with its own visited and temp-visited markers to be used in the sequencing algorithm. Once the algorithm terminates these markers are reset.

- There are also checks for duplicate naming or adding dependancies between nodes that do not exist.

- A validity checker is implemented to check if the sequence has any child occuring before a parent.
