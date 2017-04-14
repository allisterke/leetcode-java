#!/bin/bash

if [ $# -ne 1 ]; then
    echo 'please input package name'
    exit 1
fi

DIR=$1
if [[ ! $DIR =~ a.* ]]; then
    DIR=$(printf "a%03d" $DIR)
fi

mkdir $DIR
if [ $? -ne 0 ]; then
    exit 1
fi

cp Solution.java $DIR/
sed -i -r "1 s/^(.*)$/package $DIR;\n\n\1/" $DIR/Solution.java

echo $DIR/Solution.java created
