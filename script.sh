#!/bin/bash
input=$1
output=$2

# check for the existence of the text file
if [  -f "$input" ]
then
    echo "File $input exists"
else 
    echo "File $input does not exists"
fi

# run the Spray service
#exec scala "D:\Dev\Samples\Spray\src\main\scala\Main.scala"

# run the Akka app, passing in the path to the text file
#exec scala "D:\Dev\Samples\Akka\src\main\scala\Main.scala" $input

# read the output file that the Akka program produced – select the top 5 words with the highest counts
words= sort -k2 -n $output | grep -m5

# curl these words to the spray service and display the output
for w in "${words[@]}"
do	
	#echo $w "<>" 
    res= curl http://127.0.0.1:8080/reverse?word=$w
	echo $res " <> " $w
done