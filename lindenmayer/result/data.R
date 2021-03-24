pdf("plot.pdf")
par(mfrow=c(2,2))
directory<-"./data"
for (i in seq(1,4)) {
  data1 <- read.delim(paste(directory, "/data", i, "a.txt", sep=""))
  data2 <- read.delim(paste(directory, "/data", i, "b.txt", sep=""))
  data3 <- read.delim(paste(directory, "/data", i, "c.txt", sep=""))
  source("./graph.R")
}
dev.off()
