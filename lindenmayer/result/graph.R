xmax = max(data1$Year)
ymax = max(data1$Population)

color1 = "cornflowerblue"
color2 = "forestgreen"
color3 = "darkred"

x1 = data1$Year
y1 = data1$Population
x2 = data2$Year
y2 = data2$Forefathers
x3 = data3$Year
y3 = data3$Foremothers

plot(x=x1, y=y1, xlim=c(0,xmax), ylim=c(1,ymax), log="y", pch=20, col=color1, bg=color1, ylab = "", xlab="Year")
par(new=TRUE)
plot(x=x2, y=y2, xlim=c(0,xmax), ylim=c(1,ymax), log="y", pch=21, col=color2, bg=color2, ylab = "", xlab = "")
par(new=TRUE)
plot(x=x3, y=y3, xlim=c(0,xmax), ylim=c(1,ymax), log="y", pch=22, col=color3, bg=color3, ylab = "", xlab = "")

lines(x1[order(x1)], y1[order(x1)], xlim=range(x1), ylim=range(y1), lwd=1, col=color1)
lines(x2[order(x2)], y2[order(x2)], xlim=range(x2), ylim=range(y2), lwd=1, col=color2)
lines(x3[order(x3)], y3[order(x3)], xlim=range(x3), ylim=range(y3), lwd=1, col=color3)

legend("left", legend=c("Population size", "Forefathers", "Foremothers"), col=c(color1, color2, color3), lty=1:2, cex=0.5)
