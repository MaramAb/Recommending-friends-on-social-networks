##Introduction
One of the main functionalities offered by online social platforms such as Facebook and Twitter is the recommendation of new friends. This is achieved by utilizing various information about the users, but the main factor used for recommending a new friend to a user is how well these two users are connected. A social network such as Facebook can be represented as undirected graph. We can use the information contained in the graph to select the top candidate friends for a given user. There are many ways to do this, but we will focus on two methods:

#1. Popular users:
In this method, we recommend the most popular users in the graph, that is nodes with the highest degrees (number of neighbors).

#2. Common neighbors:
In this method, we recommend users who have the most common friends with the user.

##The data structures
In this section, we present the data structures we implemented.

#1. Top k priority queue
To recommend top k users, we use a priority queue that keeps only the top k elements and serves them in decreasing order of priority. For this, we wrote the class PQKImp that implements the interface PQK.

#2. Map
We used the BST implementation of a map. Hence, tha BSTMap class implements the interface Map.

##Representing the social network
To represent the friendship graph, we use the Graph interface. We used adjacency list representation, and instead of an array of lists, we used a map of lists. Each list in the map represents the neighbors of a node.
We wrote the class MGraph that implements the interface Graph using this representation.

##The friends recommender
We wrote the class Recommender that implements the two friends recommendation methods discussed above.