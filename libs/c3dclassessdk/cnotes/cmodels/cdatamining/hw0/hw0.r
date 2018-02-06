##############
# functions

#--------------------------------------------------------
# name: _subset()
# desc: design to pass variable args to subset function
#--------------------------------------------------------
mysubset <- function(dataset, ssubset) {
  	return (subset(dataset, eval(parse(text=ssubset))))
}

#--------------------------------------------------------
# name: compute_data()
# desc: computes mean, variance, standard deviation
#--------------------------------------------------------
compute_data <- function(dataset) {
	# N
	N = nrow(dataset)
	
	# x
	mn_x <- mean(dataset$x)
	vr_x <- var(dataset$x)
	std_x <- sqrt(vr_x)

	# y
	mn_y <- mean(dataset$y)
	vr_y <- var(dataset$y)
	std_y <- sqrt(vr_y)

	# correlation
	# cor_xy <- cor.test(dataset$x, dataset$y, method = "pearson")
	cor_xy <- cor(dataset$x, dataset$y)  	

	# print a summary of x
	result <- ""
	result <- paste(result,"N = ",N, '\n', sep="")
	result <- paste(result,"Mean of X = ",mn_x, '\n', sep="")
	result <- paste(result,"Variance of X = ",vr_x, '\n', sep="")
	result <- paste(result,"Standard Deviation of X = ",std_x, '\n', sep="")

	# print a summary of y
	result <- paste(result,"Mean of Y = ",mn_y, '\n', sep="")
	result <- paste(result,"Variance of Y = ",vr_y, '\n', sep="")
	result <- paste(result,"Standard Deviation of Y = ",std_y, '\n', sep="")
	
	# print correlation of x and y
	result <- paste(result,"Correlation of X and Y = ",cor_xy,"\n",sep="")
	return (result)
}

#----------------------------------------------------
# name: make_sequential_plot
# desc: 
#----------------------------------------------------
make_sequential_plot <- function(input, a) {
	plot(ts(input$x),ylab = "X", main = a)
	plot(ts(input$y),ylab = "Y", main = a)
}

#----------------------------------------------------
# name: make_scatter_plot
# desc: 
#----------------------------------------------------
make_scatter_plot <- function(input, a) {
	# Plot the chart for cars with weight between 2.5 to 5 and mileage between 15 and 30.
	plot(y = input$y,
		x = input$x,
   		xlab = "X",
   		ylab = "Y",
   		xlim = c(0,100),
   		ylim = c(0,100),		 
   		main = a, 
		abline(lm(input$x~input$y))
	)
}

#################
# main
path <- "C:\\Users\\developer\\Desktop\\cezdev2\\libs\\c3dclassessdk\\cnotes\\cmodels\\cdatamining\\hw0"
datafile <- paste(path, "w1taskCSV.csv", sep="\\")

# 1. load the dataset
ds <- read.csv(datafile, header=T)

# 2. calculate the mean, standard deviation, correlation
output <- ""
output <- paste("All the data summary\n", sep="")
output <- paste(output,compute_data(ds),"\n",sep="")
x = c('a','b','c','d','e','f','g','h','i','j','k','l')
n = length(x)
for(i in 1:n) {
	output <- paste(output, "Dataset: ", x[i], "\n", sep="")
	q <- paste("dataset==","'",x[i],"'",sep="")
	ss <- mysubset(ds, q)
	output <- paste(output, compute_data(ss), "\n", sep="")
}
file <- paste("summary.txt",sep="")
datafile <- paste(path, file, sep="\\")
fileConn<-file(datafile)
writeLines(output, fileConn)
close(fileConn)

# 3. sequential plots
file <- paste("sequential-plot-all.jpg",sep="")
datafile <- paste(path, file, sep="\\")
png(file = datafile)
par(mfrow=c(2,1))
make_sequential_plot(ds, "all")
dev.off()

file <- paste("sequential-plot.jpg",sep="")
datafile <- paste(path, file, sep="\\")
png(file = datafile)
par(mfrow=c(3,4))
x = c('a','b','c','d','e','f','g','h','i','j','k','l')
n = length(x)
for(i in 1:n) {
	q <- paste("dataset==","'",x[i],"'",sep="")
	ss <- mysubset(ds, q)
	make_sequential_plot(ss, x[i])
}
dev.off()

# 4. scatter plots
file <- paste("scatter-plot-all.jpg",sep="")
datafile <- paste(path, file, sep="\\")
png(file = datafile)
par(mfrow=c(2,1))
make_scatter_plot(ds, "all")
dev.off()

file <- paste("scatter-plot.jpg",sep="")
datafile <- paste(path, file, sep="\\")
png(file = datafile)
par(mfrow=c(3,4))
x = c('a','b','c','d','e','f','g','h','i','j','k','l')
n = length(x)
for(i in 1:n) {
	q <- paste("dataset==","'",x[i],"'",sep="")
	ss <- mysubset(ds, q)
	make_scatter_plot(ss, x[i])
}
dev.off()