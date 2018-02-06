##############
# functions

#--------------------------------------------------------
# name: _subset()
# desc: design to pass variable args to subset function
#--------------------------------------------------------
mysubset <- function(dataset, ssubset) {
  	return subset(dataset, eval(parse(text=ssubset)))
}

#--------------------------------------------------------
# name: _compute_data()
# desc: computes mean, variance, standard deviation
#--------------------------------------------------------
compute_data <- function(dataset) {
	# x
	mn_x <- mean(ds$x)
	vr_x <- var(ds$x)
	std_x <- sqrt(vr_x)

	# y
	mn_y <- mean(ds$y)
	vr_y <- var(ds$y)
	std_y <- sqrt(vr_y)

	# print a summary of x
	result <- paste(result, "Mean of X = ",mn_x, '\n', sep="")
	result <- paste(result,"Variance of X = ",vr_x, '\n', sep="")
	result <- paste(result,"Standard Deviation of X = ",std_x, '\n', sep="")

	# print a summary of y
	result <- paste(result,"Mean of Y = ",mn_y, '\n', sep="")
	result <- paste(result,"Variance of Y = ",vr_y, '\n', sep="")
	result <- paste(result,"Standard Deviation of Y = ",std_y, '\n', sep="")
	return (result)
}

#################
# main
path <- "C:\\Users\\developer\\Desktop\\cezdev2\\libs\\c3dclassessdk\\cnotes\\cmodels\\cdatamining\\hw0"
datafile <- paste(path, "w1taskCSV.csv", sep="\\")

# 1. load the dataset
ds <- read.csv(datafile, header=T)

# calculate the mean, standard deviation, correlation
result <- paste("All the data summary\n", sep=")
result <- compute_data(ds)
cat(result)

q <- paste("dataset==","'l'", sep="")
mysubset(ds, q)

x = c('a','b','c','d','e','f','g','h','i','j','k','l')

n = length(x)
for(i in 1:n) {
	s = eval(parse(text="dataset=='a'"))

  	sds <- subset(ds, s) 
	
	mn_x <- mean(sds$x)
	vr_x <- var(sds$x)
	std_x <- sqrt(vr_x)

	mn_y <- mean(sds$y)
	vr_y <- var(sds$y)
	std_y <- sqrt(vr_y)
	
	# print group
	result <- paste(result, "\n", "Group: ", x[i], "\n", sep="")

	# print a summary of x
	result <- paste(result, "Mean of X = ",mn_x, '\n', sep="")
	result <- paste(result,"Variance of X = ",vr_x, '\n', sep="")
	result <- paste(result,"Standard Deviation of X = ",std_x, '\n', sep="")

	# print a summary of y
	result <- paste(result,"Mean of Y = ",mn_y, '\n', sep="")
	result <- paste(result,"Variance of Y = ",vr_y, '\n', sep="")
	result <- paste(result,"Standard Deviation of Y = ",std_y, '\n', sep="")
	return <- paste(result, "\n\n")
}

cat(result)

