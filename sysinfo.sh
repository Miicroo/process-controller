#!/bin/bash

IFS=$'\n'
UPTIME=`uptime`
D_UP=${UPTIME:1}
MYGROUPS=`groups`
DATE=`date`
KERNEL=`uname -a`
CPWD=`pwd`
ME=`whoami`
CPU=`arch`

printf "<=== SYSTEM ===>\n"
printf "Kernel===:\t"$KERNEL"\n"
printf "Uptime===:\t"$D_UP"\n"
df -h
printf "Architecture===:\t"$CPU"\n"
cat /proc/cpuinfo | grep "model name\|processor" | awk '
/processor/{printf "Processor" $3 "===: " }
/model\ name/{
i=4
while(i<=NF){
	printf $i
	if(i<NF){
		printf " "
	}
	i++
}
printf "\n"
}'
printf "Date===:\t\t"$DATE"\n"
printf "<=== USER ===>\n"
printf "User===:\t\t"$ME" (uid:"$UID")\n"
printf "Groups===:\t"$MYGROUPS"\n"
printf "Working dir===:\t"$CPWD"\n"
printf "Home dir===:\t"$HOME"\n"
printf "<=== NETWORK ===>\n"
printf "Hostname===:\t"$HOSTNAME"\n"