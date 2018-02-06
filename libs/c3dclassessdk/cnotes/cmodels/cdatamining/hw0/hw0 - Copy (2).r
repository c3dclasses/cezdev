
path <- "C:\\Users\\developer\\Desktop\\cezdev2\\libs\\c3dclassessdk\\cnotes\\cmodels\\cdatamining\\hw0"
datafile <- paste(path, "w1taskCSV.csv", sep="\\")

# 1. load the dataset
ds <- read.csv(datafile, header=T)

# calculate the mean, standard deviation, correlation

# x
mn_x <- mean(ds$x)
vr_x <- var(ds$x)
std_x <- sqrt(vr_x)

# y
mn_y <- mean(ds$y)
vr_y <- var(ds$y)
std_y <- sqrt(vr_y)

# print a summary of x
result <- paste("Mean of X = ",mn_x, '\n')
result <- paste(result,"Variance of X = ",vr_x, '\n')
result <- paste(result,"Standard Deviation of X = ",std_x, '\n')
# print a summary of y
result <- paste(result,"Mean of Y = ",mn_y, '\n')
result <- paste(result,"Variance of Y = ",vr_y, '\n')
result <- paste(result,"Standard Deviation of Y = ",std_y, '\n')

# get the subset of all the data
# subsets <- ds$dataset[!duplicated(ds$dataset)]
# result <- ""
# result <- paste(result, "Subsets = ", subsets, "\n")
# print results
# cat(result)
# cat(subsets)

x = c('a','b','c','d','e','f','g','h','i','j','k','l')
n = length(x)
for(i in 1:n) {
  	sds <- subset(ds, dept == x[i]) 
	
	mn_x <- mean(ds$x)
	vr_x <- var(ds$x)
	std_x <- sqrt(vr_x)

	mn_y <- mean(sds$y)
	vr_y <- var(sds$y)
	std_y <- sqrt(vr_y)
}

# Create the data for the chart.
# ds_x <- dataset$x
# ds_y <- dataset$y
# Give the chart file a name.
# png(file = "C:\\Users\\developer\\Desktop\\cezdev2\\libs\\c3dclassessdk\\cnotes\\cmodels\\cdatamining\\hw0\\line_chart_2_lines.jpg")
# Plot the bar chart.
# plot(ds_x, type = "o", col = "red", xlab = "X", ylab = "Y", main = "X / Y")
# lines(ds_y, type = "o", col = "blue")
# Save the file.
# dev.off()

library(ggplot2)
# Line plot with multiple groups
ggplot(data=dataset, aes(x=x, y=dataset)) + geom_line() + geom_point()
