#!/bin/bash
input=$1

# check for the existence of the text file
if [  -f "$input" ]
then
    echo "File $input exists"
else 
    echo "File $input does not exists"
fi

# run the Akka app, passing in the path to the text file
java -jar ./MyAkka.jar $input &

# run the Spray service
java -jar ./MySpray.jar &
sleep 1

# read the output file that the Akka program produced – select the top 5 words with the highest counts
words=$(cat ./result.txt | sort -n -k2 | tail -n5 | cut -d' ' -f1)

# curl these words to the spray service and display the output
for w in $words
do	
	echo $w ' > '
    res= curl http://localhost:8081/reverse?word=$w
	echo $res	
done