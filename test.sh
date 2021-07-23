#!/bin/bash

Port_List=(6666 6667 6668)
resultPort=""
while true
do
  for port in ${Port_List[*]}
  do
      echo "Prepare to check port:$port"
      result="$(adb devices | grep "$port" | cut -c 1-21)"
      echo "$result"
      if [ "$result" == "" ]; then
          echo "we can use this port $port !!"
          resultPort=$port
          break 2
      elif [[ $result == *"device"* ]]; then
            echo "don't use this port $port"
      elif [[ $result == *"offline"* ]]; then
            echo "don't use this port $port"
      else
            echo "wtf??!! $port"
      fi
  done
  echo "sleep 30s and wait device"
  sleep 30
done



DEVICE="PRCheckEmulator${resultPort}"
EMULATOR="/Users/i530643/Library/Android/sdk/emulator/emulator"
$EMULATOR -port "$resultPort" @"$DEVICE" &





