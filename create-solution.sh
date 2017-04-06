#!/bin/bash

if [ $# -ne 1 ]; then
    echo 'please input package name'
    exit 1
fi

mkdir -p $1
cp Solution.java $1/
sed -i -r "1 s/^(.*)$/package $1;\n\n\1/" $1/Solution.java
