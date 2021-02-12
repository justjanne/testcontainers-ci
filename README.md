# Testcontainers-CI

Testcontainers-CI allows you to easily use testcontainers whenever you can, but use containers created through other 
means whenever you can’t.

When running your CI in docker, you can’t launch new containers without DinD, but thanks to Gitlab Ci Services you can 
run a docker container with the desired service and use it in your code easily.

This project simplifies this process, by providing a façade for both types of containers.

## Using Testcontainers-CI

After adding this module to your dependencies, you can just wrap your calls creating testcontainers with the 
`providedContainer` function, and it’ll return either the Testcontainer, or the provided container, whichever is
available in the current environment. 

```kotlin
@CiContainers
class MyLittleTest {
  private val container = providedContainer("REDIS_CONTAINER") {
    GenericContainer(DockerImageName.parse("redis:5.0.3-alpine"))
      .withExposedPorts(6379);
  }
  
  @Test
  fun test() {
    println(container.address)
    println(container.getMappedPort(6379))
  }
}
```

```yaml
  services:
    - name: "redis:5.0.3-alpine"
      alias: "test_redis_instance"
  variables:
    REDIS_CONTAINER: "test_redis_instance"
```
