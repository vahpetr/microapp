# https://docs.docker.com/get-started/part4/
# https://docs.docker.com/engine/userguide/networking/get-started-overlay
# https://docs.docker.com/swarm/swarm_at_scale/deploy-infra
# https://github.com/docker/labs/tree/master/security/swarm
# https://docs.docker.com/engine/userguide/networking/get-started-overlay

### logs
### https://docs.docker.com/engine/admin/logging/logentries/
### logs

# see all docker env vars
# env | grep DOCKER

set -e

MASTER_COUNT=1
WORKER_COUNT=2

# unset all DOCKER variables in the current shell
# eval $(docker-machine env -u)

for i in $(seq $MASTER_COUNT); do

    echo "-- Create machine manager$i"
    docker-machine create \
        --driver virtualbox \
        --virtualbox-disk-size "2500" \
        --virtualbox-memory "512" \
        manager$i

    if [ $i -gt 1 ]
    then
        echo "-- Join manager$i to manager1"
        eval "$(docker-machine env manager1)"
        TOKEN=$(docker swarm join-token -q manager)
        eval "$(docker-machine env manager$i)"
        docker swarm join --token $TOKEN `docker-machine ip manager1`:2377
    else
        echo "-- Swarm init on manager$i"
        eval "$(docker-machine env manager$i)"
        docker swarm init \
            --advertise-addr="$(docker-machine ip manager$i)" \
            --listen-addr="$(docker-machine ip manager$i)":2377
    fi

    wait

    echo "-- Info manager$i"
    docker info

    TOKEN=$(docker swarm join-token -q worker)
    echo "-- Swarm token on manager$i $TOKEN"

    for j in $(seq $WORKER_COUNT); do

        wait

        echo "-- Create machine manager$i-worker$j"
        docker-machine create \
            --driver virtualbox \
            --virtualbox-disk-size "2500" \
            --virtualbox-memory "512" \
            manager$i-worker$j

        echo "-- Join worker$j to manager$i"
        docker-machine ssh manager$i-worker$j docker swarm join --token $TOKEN `docker-machine ip manager$i`:2377

        echo "-- Info manager$i-worker$j"
        docker info

    done

    wait

    # if [ $i -gt 1 ]
    # then
    #     echo "-- Join manager1 to manager$i"
    #     eval "$(docker-machine env manager1)"
    #     TOKEN=$(docker swarm join-token -q manager)
    #     eval "$(docker-machine env manager$i)"

    #     docker swarm join --token $TOKEN `docker-machine ip manager1`:2377
    #     wait
    # fi

    echo "-- Info manager$i"
    docker info

    echo "-- Info nodes manager$i"
    docker node ls

done

# docker-machine rm -y -f manager1 manager2 manager1-worker1 manager1-worker2 manager2-worker1 manager2-worker2






# echo "-- Create machine keystore"
# docker-machine create \
#     --driver virtualbox \
#     --virtualbox-disk-size "2500" \
#     --virtualbox-memory "512" \
#     --engine-opt="label=com.function=consul" \
#     keystore

# eval $(docker-machine env keystore)

# docker run --restart=unless-stopped -d -p 8500:8500 -h consul progrium/consul -server -bootstrap

# for i in $(seq $MASTER_COUNT); do

#     docker-machine create \
#         --driver virtualbox \
#         --virtualbox-disk-size "2500" \
#         --virtualbox-memory "512" \
#         --engine-opt="label=com.function=manager" \
#         --engine-opt="cluster-store=consul://$(docker-machine ip keystore):8500" \
#         --engine-opt="cluster-advertise=eth1:2376" \
#         manager$1

#     eval $(docker-machine env manager$1)

#     docker run --restart=unless-stopped -d -p 3376:2375 \
#         -v /var/lib/boot2docker:/certs:ro \
#         swarm manage --tlsverify \
#         --tlscacert=/certs/ca.pem \
#         --tlscert=/certs/server.pem \
#         --tlskey=/certs/server-key.pem \
#         consul://$(docker-machine ip keystore):8500

#     docker-machine ssh manager1 'tail /var/lib/boot2docker/docker.log'

# done

# docker-machine create \
#     --driver virtualbox \
#     --virtualbox-disk-size "2500" \
#     --virtualbox-memory "512" \
#     --engine-opt="label=com.function=interlock" \
#     loadbalancer

# eval $(docker-machine env loadbalancer)

# docker run \
#     -P \
#     -d \
#     -ti \
#     -v nginx:/etc/conf \
#     -v /var/lib/boot2docker:/var/lib/boot2docker:ro \
#     -v /var/run/docker.sock:/var/run/docker.sock \
#     -v $(pwd)/interlock/config.toml:/etc/config.toml \
#     --name interlock \
#     ehazlett/interlock:1.3.0 \
#     -D run -c /etc/config.toml

# docker logs interlock
