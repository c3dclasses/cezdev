# library(datasets)
# head(iris)
# library(ggplot2)
# ggplot(iris, aes(Petal.Length, Petal.Width, color = Species)) + geom_point()
dataset = read.csv("C:/Users/developer/Desktop/cezdev/libs/c3dclassessdk/ccore/calgorithms/cprinciplecomponentanalysis/iris-2d.dat")
set.seed(20)
dataset

Cluster <- kmeans(dataset, 3, nstart = 20)
Cluster$cluster <- as.factor(Cluster$cluster)
ggplot(Cluster, aes(color = Cluster$cluster)) + geom_point()