data1 <- read.delim("~/Documents/Umontreal/Git/IFT2015-H21/lindenmayer/result/data1.txt")
#View(data1)
data2 <- read.delim("~/Documents/Umontreal/Git/IFT2015-H21/lindenmayer/result/data2.txt")
#View(data2)
data3 <- read.delim("~/Documents/Umontreal/Git/IFT2015-H21/lindenmayer/result/data3.txt")
#View(data3)

xmax = max(data1$Year)
ymax = max(data1$Population)

color1 = "cornflowerblue"
color2 = "forestgreen"
color3 = "darkred"

pdf("rplot.pdf")

plot(x=data1$Year, y=data1$Population, xlim=c(0,xmax), ylim=c(1,ymax), log="y", pch=21, col=color1, bg=color1, xlab="Year", ylab="")
par(new=TRUE)
plot(x=data2$Year, y=data2$Forfathers, xlim=c(0,xmax), ylim=c(1,ymax), log="y", pch=21, col=color2, bg=color2, xlab = "",ylab = "")
par(new=TRUE)
plot(x=data3$Year, y=data3$Formothers, xlim=c(0,xmax), ylim=c(1,ymax), log="y", pch=22, col=color3, bg=color3, xlab = "", ylab = "")

x=data1$Year
y=data1$Population
lines(x[order(x)],y[order(x)], xlim=range(x), ylim=range(y),lwd=5, col=color1)

x=data2$Year
y=data2$Forfathers
lines(x[order(x)],y[order(x)], xlim=range(x), ylim=range(y),lwd=2, col=color2)

x=data3$Year
y=data3$Formothers
lines(x[order(x)],y[order(x)], xlim=range(x), ylim=range(y),lwd=2, col=color3)

legend(500, 500, legend=c("Population size", "Forefather","Foremother"), col=c(color1, color2,color2), lty=1:2, cex=0.8)

dev.off() 
