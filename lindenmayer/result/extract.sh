for i in {1..4}
do
    java -jar pedigree.jar 5000 50000 > data"$i".txt

    VAR1="$(grep -n "Forefathers" data""$i"".txt | cut -d: -f1)"
    fin1=$(( VAR1 - 2 ))

    VAR2="$(grep -n "Foremothers" data""$i"".txt | cut -d: -f1)"
    fin2=$(( VAR2 - 2 ))

    sed -n 1,"$fin1"p data"$i".txt > data"$i"a.txt
    sed -n "$VAR1","$fin2"p data"$i".txt > data"$i"b.txt
    sed -n ''"${VAR2}"',$p' data"$i".txt > data"$i"c.txt
done

Rscript ./script.R