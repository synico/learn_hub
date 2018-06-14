#!/bin/bash

echo $1 $2 "$3"

for sub in `ls`
do
  if [ -d $sub ]
  then
    echo $sub
    git -C $PWD/$sub $1 $2 "$3"
    echo "------------------------------------------"
  fi
done
