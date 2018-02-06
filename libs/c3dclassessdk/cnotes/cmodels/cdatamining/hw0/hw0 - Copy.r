dataset <- read.csv(file.choose(), header=T)
print(is.data.frame(dataset))
print(ncol(dataset))
print(nrow(dataset))
mn <- mean(dataset$x)
print(paste("Mean of X = ",mn))
vr <- var(dataset$x)
print(paste("Variance of X = ",vr))
std <- sqrt(vr)
print(paste("Standard Deviation of X = ",std))


res <- cor.test(dataset$x, dataset$y, 
                    method = "pearson")
res
