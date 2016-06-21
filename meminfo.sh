#!/bin/bash
# meminfo.sh - Displays system memory info
#
# Taken from: https://bash.cyberciti.biz/guide/Getting_information_about_your_system
# Linux server / desktop.
# Author: Vivek Gite
# Date: 12/Sep/2007


function write_header(){
	local h="$@"
	echo "---------------------------------------------------------------"
	echo "     ${h}"
	echo "---------------------------------------------------------------"
}

write_header " Free and used memory "
free -m

echo "*********************************"
echo "*** Virtual memory statistics ***"
echo "*********************************"
vmstat
echo "***********************************"
echo "*** Top 5 memory eating process ***"
echo "***********************************"	
ps auxf | sort -nr -k 4 | head -5