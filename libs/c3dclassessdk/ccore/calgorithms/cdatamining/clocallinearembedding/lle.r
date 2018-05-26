library(lle)

#load the MNIST dataset of the 9 digit
X <- read.csv("C:/Users/developer/Desktop/cezdev/libs/c3dclassessdk/ccore/calgorithms/cdatamining/cisomap/mnist-digit-9-5000-obs.csv", header=T)

# convert the dataframe to a matrix
X_mat = as.matrix(X)

# perform lle
k5_df_lle <- lle(X, m=2, k=50, reg=2, ss=FALSE, id=TRUE, v=0.9)

# plot the isomap
plot(k5_df_lle$Y, main="K=5 data")

# save the isomap
write.csv(k5_df_lle$Y, file = "C:/Users/developer/Desktop/cezdev/libs/c3dclassessdk/ccore/calgorithms/cdatamining/cisomap/mnist-digit-9-5000-lle-k20.csv",row.names=FALSE)
