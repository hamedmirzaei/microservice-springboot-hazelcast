# Spring Boot + Hazelcast IMDG

This is a simple example to show you how Hazelcast IMDG works with Spring Boot for microservice purposes.

# Libraries and Tools
* [Module] [`Spring Boot`](https://spring.io/projects/spring-boot)
* [Tool] [`Hazelcast IMDG`](https://hazelcast.org/)
* [Library for auto-generating getters, setters, constructors and others] [`Lombok`](https://projectlombok.org/)

# How it works
This demo demonstrates how you can using `Hazelcast IMDG` in microservice context using `Spring Boot`
to utilize clustering and sharing of data + caching of data.

* Start `hazelcast-instance1`: It will be listen on port 8091
* Start `hazelcast-instance2`: It will be listen on port 8092

By starting both of the above modules, you will get a cluster of two members over `Hazelcast IMDG`.

You are now able to send following requests:
* Send `POST` to http://localhost:8091/hazelcast1/write-data?key=hamed&value=mirzaei will save 
key/value `hamed/mirzaei` in the hazelcast map.
* Send `GET` to http://localhost:8091/hazelcast1/read-data?key=hamed will get the value `mirzaei` 
for the key `hamed` stored in the hazelcast map: for all the `hamed` keys, hazelcast will cache
the results.
* Send `GET` to http://localhost:8091/hazelcast1/read-all-data will get all the key/value pairs
stored within hazelcast.

At the same time you can call the second module by simply replacing `http://localhost:8091/hazelcast1`
with `http://localhost:8092/hazelcast2`. You can check that the data is shared between the two services.


