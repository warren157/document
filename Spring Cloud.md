# Spring Cloud

## 父 pom的依赖

```
引入springcloud 的依赖
<dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>2021.0.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```



##  搭建 eureka

### 1.依赖

```
1·依赖
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
```

### 2.创建EurekaApplication

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer.class);
    }
}
```

### 3.配置application.yml

```
server:
  port: 8761
spring:
  application:
    name: eureka
  eureka:
    client:
      registerWithEureka: false
      fetchRegistry: false
    server:
      waitTimeInMsWhenSyncEmpty: 0
  management:
    endpoints:
      web:
        exposure:
          include: "*"
  #禁用spring.config.import
  cloud:
    config:
      enabled: false
```



## Producer 服务提供者

### 1.依赖

```
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        
        <!--需要注意的是：其实应该要引入openfeign依赖，但是我在service模块中引入过了，这里就不需要再次引入 -->
```

### 2.创建ProducerApplication

```
@EnableEurekaClient
@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class);
    }
}
```

### 3.创建application.yml

```
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
server:
  port: 8899


spring:
  application:
    name: PRODUCER
```

## Consumer 服务调用者

### 1.pom依赖

```
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <dependency>
            <groupId>com.cloud</groupId>
            <artifactId>service</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

### 2.创建ConsumerApplication

```
//开启eureka客户端，服务注册到eureka中
@EnableEurekaClient
@SpringBootApplication
//开启openfeign，接口调用通过feign
@EnableFeignClients
public class ConsumerApplication {
    public static void main(String[] args) {

        SpringApplication.run(ConsumerApplication.class);
    }
}
```

### 3.创建application.yml

```
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
server:
  port: 8898


spring:
  application:
    name: CONSUMER
```

## 接口层（api）

### 1.代码

```
//说明调用的服务名称
@FeignClient(value="PRODUCER")
@Component
public interface HelloWorldService {

//接口api
    @GetMapping("/sayHello")
    String sayHello(@RequestParam("name") String name);
}
```



## Openfeign Load Balancer负载均衡

```

@Configuration
public class LoadbalanceConfig {


    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
                                                            LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RoundRobinLoadBalancer(loadBalancerClientFactory
                .getLazyProvider(name, ServiceInstanceListSupplier.class),
                name);
    }
}


//RoundRobinLoadBalancer轮训
//RandomRobinLoadBalancer随机
```

## 问题
```
1.为什么要用springcloud微服务，解决了什么问题
2.eureka中注册的是什么信息
```
