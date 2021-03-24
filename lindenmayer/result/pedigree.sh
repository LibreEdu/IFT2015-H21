#!/bin/zsh
:'
for i in {1..4}
do
    java -jar pedigree.jar 5000 50000 > ./data/data"$i".txt

    VAR1="$(grep -n "Forefathers" ./data/data""$i"".txt | cut -d: -f1)"
    fin1=$(( VAR1 - 2 ))

    VAR2="$(grep -n "Foremothers" ./data/data""$i"".txt | cut -d: -f1)"
    fin2=$(( VAR2 - 2 ))

    sed -n 1,"$fin1"p ./data/data"$i".txt > ./data/data"$i"a.txt
    sed -n "$VAR1","$fin2"p ./data/data"$i".txt > ./data/data"$i"b.txt
    sed -n ''"${VAR2}"',$p' ./data/data"$i".txt > ./data/data"$i"c.txt
done
'
Rscript ./data.R