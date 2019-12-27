#!/bin/bash

## This is to run the first program WModel.java

if [ -e "YinshBot_v2.class" ]; then
    java YinshBot_v2 $1
else
    echo "First run build.sh to compile the code"
fi