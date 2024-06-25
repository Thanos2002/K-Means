# K-Means Clustering Implementation 
 
### Description
The goal of this exercise is to develop a clustering program that uses the K-Means algorithm to cluster data points into M clusters. M will be specified as a static variable. The program will load a file containing the examples and will execute the K-Means algorithm with M clusters, ultimately saving the coordinates of the cluster centers.

### Steps:
1. **Initialization**: The initial position of each cluster center is chosen randomly from the examples.
2. **Execution**: The algorithm is executed, clustering the data points into M clusters.
3. **Calculation of Error**: At the end of the execution, the program calculates and prints the clustering error. This is done by:
   - Computing the Euclidean distance \(||x_i - μ_k||^2\) from each example \(x_i\) to the center \(μ_k\) of the cluster to which it belongs.
   - Summing these distances for all examples \(x_i\).

### Execution on Dataset
Execute the clustering program on a dataset for M = 3, 6, 9, 12 clusters. For each value of M, perform the following:

1. **Multiple Runs**: Execute 15 runs of the algorithm with different random initial centers to find the run with the minimum clustering error.
2. **Visualization**: Plot the data points and the positions of the cluster centers (e.g., data points as '+' and centers as '*') on the same graph. This is done ny using a Python script.

### Summary
This project involves implementing a K-Means clustering algorithm, executing it on a dataset with varying numbers of clusters, and analyzing the clustering error to determine the optimal number of clusters.
