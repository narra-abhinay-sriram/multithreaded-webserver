﻿# Multithreaded Web Server
->client -domain name-DNS-MAPS ip address
-> cleint establishes tcp 3 way handshake with server and following requests will be done using https protocol
-> hhtp1.0- non persistent [client sends requests to server ,server sends data ,cleint breaks the connection]
-> http1.1- persistent[client maintains connection with server untill time out]


-> client [has its socket open with its own ip and port]
->server[has its own socket open with its own ip and port and server is listening for client requests on his socket with socket.listen()]
:when ever client sends request for server ,it will make connection with socket with socket.listen(),the current socket will create an another socket with socket.accept() after establishing tcp three way handshake ,and the socket.listen() socket will listen for another client requests .

if server has only single cpu , for a client req it will open a socket ,and for second client it will make that client wait untill 1st client completes serving or rejects the request.[single threaded].

multi threaded- if server has multiple cpu crores, it makes thread of each cleint req,and context switches between threads for new client requets (ex client with lesser execution timee will be executed 1st leaving the previous prev thread in sleep mode ),but for every thread we need to create a thread block, suppose if there are 10k threads we need to create 10k thread blocks and it will eat all the memory spaces

so we have thread pooling, server has all ready made threads available in a thread pool and a queue will be placed before each thread ,client req will be sent to the queue and then it will be submitted to the thread

