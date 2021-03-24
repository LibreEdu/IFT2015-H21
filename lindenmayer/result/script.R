pdf("plot.pdf")
par(mfrow=c(2,2))
for (i in seq(1,4)) {
  data1 <- read.delim(paste("./data", i, "a.txt", sep=""))
  data2 <- read.delim(paste("./data", i, "b.txt", sep=""))
  data3 <- read.delim(paste("./data", i, "c.txt", sep=""))
  source("./graph.R")
}
dev.off()
