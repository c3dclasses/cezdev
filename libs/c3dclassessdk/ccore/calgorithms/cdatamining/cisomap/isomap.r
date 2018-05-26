# source("https://bioconductor.org/biocLite.R")
# biocLite("RDRToolbox")
library(RDRToolbox)

#load the MNIST dataset of the 9 digit
X <- read.csv("C:/Users/developer/Desktop/cezdev/libs/c3dclassessdk/ccore/calgorithms/cdatamining/cisomap/mnist-digit-9-5000-obs.csv", header=T)

# convert the dataframe to a matrix
X_mat = as.matrix(X)

# perform isomap
k5_df <- as.data.frame(Isomap(X_mat, dims=2, k=5))

# plot the isomap
plot(k5_df, main="K=5 data")

# save the isomap
write.csv(k5_df, file = "C:/Users/developer/Desktop/cezdev/libs/c3dclassessdk/ccore/calgorithms/cdatamining/cisomap/mnist-digit-9-5000-isomap-k5.csv",row.names=FALSE)



#library(ggplot2)
#ggplot(k5_2) + geom_point()
#head(k15)
#K = 15
#k15 <- Isomap(X, m=2)
#plot(k15$Y, main="K=15 data", xlab=expression(y[1]), ylab=expression(y[2]))

#K = 40
#k40 <- Isomap(X, m=2)
#plot(k40$Y, main="K=40 data", xlab=expression(y[1]), ylab=expression(y[2]))

