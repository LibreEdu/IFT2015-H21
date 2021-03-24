#!/bin/zsh

DIRECTORY="./data"
POPULATION_SIZE=1000
MAXIMUM_TIME=10000

for i in {1..4}
do
    java -jar pedigree.jar $POPULATION_SIZE $MAXIMUM_TIME > "${DIRECTORY}/data${i}.txt"

    VAR1="$(grep -n "Forefathers" ${DIRECTORY}/data${i}.txt | cut -d: -f1)"
    fin1=$(( VAR1 - 2 ))

    VAR2="$(grep -n "Foremothers" ${DIRECTORY}/data${i}.txt | cut -d: -f1)"
    fin2=$(( VAR2 - 2 ))

    sed -n 1,"$fin1"p "${DIRECTORY}/data${i}.txt" > "${DIRECTORY}/data${i}a.txt"
    sed -n "$VAR1","$fin2"p "${DIRECTORY}/data${i}.txt" > "${DIRECTORY}/data${i}b.txt"
    sed -n ''"${VAR2}"',$p' "${DIRECTORY}/data${i}.txt" > "${DIRECTORY}/data${i}c.txt"
done

Rscript ./data.R