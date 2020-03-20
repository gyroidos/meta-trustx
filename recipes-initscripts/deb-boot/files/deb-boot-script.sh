#!/bin/sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin

# setup hostname and nameserver
if [ -x /sbin/cservice ]
then
	/sbin/cservice
elif [ -x /sbin/cml-service-container ]
then
	/sbin/cml-service-container
fi

# sleep forever to keep container alive
while true; do sleep 86400; done
