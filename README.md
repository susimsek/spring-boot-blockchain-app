# Spring Boot Blockchain App
> This is spring boot hyperledger fabric example.

## Hyperledger Fabric
<img src="https://github.com/susimsek/spring-boot-blockchain-app/blob/master/images/hyperledger-fabric-logo.png" alt="Hyperledger Fabric" width="100%" height="100%"/>


## Hyperledger Explorer
<img src="https://github.com/susimsek/spring-boot-blockchain-app/blob/master/images/hyperledger-explorer-logo.png" alt="Hyperledger Explorer" width="100%" height="100%"/>

## Spring Boot
![Hyperledger Fabric](https://github.com/susimsek/spring-boot-blockchain-app/blob/master/images/spring-boot-logo.png?raw=true){:height="50%" width="50%"}

## Prerequisites

* Jdk 1.8
* Maven 3.x
* Docker 19.03.x
* Docker Compose 1.25.x

## Installation

```sh
cd first-network
```

```sh
./byfn.sh up -a -s couchdb
```

```sh
cd ..
```

```sh
sudo chmod +x build.sh
```

```sh
./build.sh
```

```sh
docker-compose up -d
```


## App Images

> Url : http://localhost:8081/explorer

![Fabric Explorer](https://github.com/susimsek/spring-boot-blockchain-app/blob/master/images/fabric-explorer.png?raw=true){:height="50%" width="50%"}

> Url : http://localhost:8090
> Username : admin
> Password : adminpw

![Hyperledger Explorer](https://github.com/susimsek/spring-boot-blockchain-app/blob/master/images/hyperdger-explorer.png?raw=true){:height="50%" width="50%"}


## Used Technologies

* Spring Boot 2.2.6
* Hyperledger Fabric
* Hyperledger Explorer
* Fabric Explorer

