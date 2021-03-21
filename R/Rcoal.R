data <- read.delim("~/data.txt")
plot(x=data$Year, y=data$Population,ylim=c(1,12000),  xlim=c(0,20000), log="y",pch=21, col="cornflowerblue", xlab="Year", ylab="", bg="cornflowerblue")
par(new=TRUE)
plot(x=data$YearF, y=data$Forfather, log="y",pch=21, col="forestgreen",xlab = "",ylab = "",ylim=c(1,12000),  xlim=c(0,20000), bg="forestgreen")
par(new=TRUE)
plot(x=data$YearM, y=data$Formother,ylim=c(1,12000),  xlim=c(0,20000), log="y",pch=22,col="darkred", xlab = "", ylab = "", bg="darkred")
x=data$Year
y=data$Population
lines(x[order(x)],y[order(x)], xlim=range(x), ylim=range(y),lwd=5, col="cornflowerblue")
x=data$YearF
y=data$Forfather
lines(x[order(x)],y[order(x)], xlim=range(x), ylim=range(y),lwd=2, col="forestgreen")
x=data$YearM
y=data$Formother
lines(x[order(x)],y[order(x)], xlim=range(x), ylim=range(y),lwd=2, col="darkred")
legend(500, 500, legend=c("Population size", "Forefather","Foremother"), col=c("cornflowerblue", "forestgreen","darkred"), lty=1:2, cex=0.8)


