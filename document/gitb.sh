#!/bin/bash

echo $1 $2 "$3"

for sub in `ls`
do
  if [ -d $sub ]
  then
    echo $sub
    cd $sub
    git $1 $2 "$3"
    cd ..
    echo "------------------------------------------"
  fi
done
