#load the MNIST dataset of the 9 digit
points <- read.csv("C:/Users/developer/Desktop/cezdev/libs/c3dclassessdk/ccore/cdatastructures/cmatrix/Mpoints.txt", header=F)
# grid <- read.csv("C:/Users/developer/Desktop/cezdev/libs/c3dclassessdk/ccore/cdatastructures/cmatrix/Mgrid.txt", header=F)

library(ggplot2)
ggplot(points, aes(V1,V2)) + geom_point(aes(colour = factor(points$V3))) 
# plot(points)

# plot(points, type="l", col="red" )
# par(new=TRUE)
# plot(grid, type="l", col="green" )

