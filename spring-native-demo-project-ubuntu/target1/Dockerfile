FROM ubuntu:18.04

WORKDIR /app

ADD spring-native-demo-project /app

# install native-image package lib

RUN apt install g++

RUN apt install zlib1g


# expose port 8080
EXPOSE 8080

# run project
CMD ["./spring-native-demo-project"]