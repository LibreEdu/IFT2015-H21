VAR1="$(grep -n "Forfathers" data.txt | cut -d: -f1)"
fin1=$(( VAR1 - 2 ))

VAR2="$(grep -n "Formothers" data.txt | cut -d: -f1)"
fin2=$(( VAR2 - 2 ))

sed -n 1,"$fin1"p data.txt > data1.txt
sed -n "$VAR1","$fin2"p data.txt > data2.txt

sed -n ''"${VAR2}"',$p' data.txt > data3.txt
