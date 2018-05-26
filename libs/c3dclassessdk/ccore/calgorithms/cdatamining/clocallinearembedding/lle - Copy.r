library(lle)

# get the data 
data(lle_scurve_data)
X <- lle_scurve_data
head(X, n=3)

# constrcuct neighbor matrix
neighbors <- find_nn_k(X, k=15)
neighbors[1:6, 1:6]

# calculate weights between x and it's neighbors
weights <- find_weights(neighbors, X, m=2, reg=2)
weights$wgts[1:6, 1:6]


# perform lle
library(scatterplot3d)

# the 3-D graph of the original data
scatterplot3d(x=X[,1], y=X[,2], z=X[,3], color=X[,2])

# perform lle
K = 5
k5 <- lle(X, m=2, k=5, reg=2, ss=FALSE, id=TRUE, v=0.9)
plot(k5$Y, main="K=5 data", xlab=expression(y[1]), ylab=expression(y[2]))

K = 15
k15 <- lle(X, m=2, k=15, reg=2, ss=FALSE, id=TRUE, v=0.9)
plot(k15$Y, main="K=15 data", xlab=expression(y[1]), ylab=expression(y[2]))

K = 40
k40 <- lle(X, m=2, k=40, reg=2, ss=FALSE, id=TRUE, v=0.9)
plot(k40$Y, main="K=40 data", xlab=expression(y[1]), ylab=expression(y[2]))


