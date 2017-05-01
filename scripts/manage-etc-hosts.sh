#!/bin/bash

# copy from https://gist.github.com/irazasyed/a7b0a079e7727a4315b9

# how use it
# manage-etc-hosts.sh function:addhost|removehost $HOSTNAME $IP

ETC_HOSTS=/etc/hosts

HOSTNAME=$2
IP=$3

addhost() {
    echo "Adding host";
    HOSTS_LINE="$IP\t$HOSTNAME"
    if [ -n "$(grep $HOSTNAME /etc/hosts)" ]
        then
            echo "$HOSTNAME already exists : $(grep $HOSTNAME $ETC_HOSTS)"
        else
            echo "Adding $HOSTNAME to your $ETC_HOSTS";
            sudo -- sh -c -e "echo '$HOSTS_LINE' >> /etc/hosts";

            if [ -n "$(grep $HOSTNAME /etc/hosts)" ]
                then
                    echo "Force renew dns"
                    dscacheutil -flushcache

                    echo "$HOSTNAME was added succesfully \n $(grep $HOSTNAME /etc/hosts)";
                else
                    echo "Failed to Add $HOSTNAME, Try again!";
            fi
    fi
}

removehost() {
    echo "Removing host";
    if [ -n "$(grep $HOSTNAME /etc/hosts)" ]
    then
        echo "$HOSTNAME Found in your $ETC_HOSTS, Removing now...";
        sudo sed -i".bak" "/$HOSTNAME/d" $ETC_HOSTS

        echo "Force renew dns"
        dscacheutil -flushcache
    else
        echo "$HOSTNAME was not found in your $ETC_HOSTS";
    fi
}

$@
