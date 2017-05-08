#!/bin/bash

# exit if error
set -e

echo "-- Up cluster begin"

# tolerance and distributions
# https://docs.docker.com/engine/swarm/admin_guide/#add-manager-is-for-fault-tolerance

# i think optimal starter configuration for test 3+3x3, minimal 2+2x2

MANAGER_COUNT=2
echo "-- Managers count: $MANAGER_COUNT"
WORKER_COUNT=2
echo "-- Workers count: $WORKER_COUNT"

echo "-- Creating $MANAGER_COUNT manager machines";
for i in $(seq 1 $MANAGER_COUNT);
do
    echo "-- Creating manager$i machine";
    docker-machine create \
        --driver virtualbox \
        --virtualbox-disk-size "2500" \
        --virtualbox-memory "512" \
        manager$i;

    echo "-- Creating $WORKER_COUNT worker on manager$i";
    for j in $(seq 1 $WORKER_COUNT);
    do
        echo "-- Creating worker$j on manager$i";
        docker-machine create \
            --driver virtualbox \
            --virtualbox-disk-size "2500" \
            --virtualbox-memory "512" \
            manager$i-worker$j;
    done
done

echo "-- List all machines"
docker-machine ls

echo "-- Initializing first swarm manager"
docker-machine ssh manager1 "docker swarm init --listen-addr $(docker-machine ip manager1) --advertise-addr $(docker-machine ip manager1)"

unset MANAGER_TOKEN
export MANAGER_TOKEN=`docker-machine ssh manager1 "docker swarm join-token manager -q"`
echo "-- Manager token: $MANAGER_TOKEN"

echo "-- Other masters join swarm"
for i in $(seq 2 $MANAGER_COUNT);
do
    echo "-- manager$i joining swarm as manager"
    docker-machine ssh manager$i \
        "docker swarm join \
        --token $MANAGER_TOKEN \
        --listen-addr $(docker-machine ip manager$i) \
        --advertise-addr $(docker-machine ip manager$i) \
        $(docker-machine ip manager1)"
done

for i in $(seq 1 $MANAGER_COUNT);
do
    unset WORKER_TOKEN
    export WORKER_TOKEN=`docker-machine ssh manager$i "docker swarm join-token worker -q"`
    echo "-- Worker token on manager$i: $WORKER_TOKEN"

    echo "-- Workers join swarm"
    for j in $(seq 1 $WORKER_COUNT);
    do
        echo "-- manager$i-worker$j joining swarm as worker to manager$i"
        docker-machine ssh manager$i-worker$j \
            "docker swarm join \
            --token $WORKER_TOKEN \
            --listen-addr $(docker-machine ip manager$i-worker$j) \
            --advertise-addr $(docker-machine ip manager$i-worker$j) \
            $(docker-machine ip manager$i)"
    done
done

echo "-- Show members of swarm"
docker-machine ssh manager1 "docker node ls"

echo "-- Up cluster end"
