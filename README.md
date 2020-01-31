# naitve-image-examples
Repository containing a few native image examples

# Pre-reqs
- GraalVM's native image
- Gradle

# Build and Run
- ./gradlew assemble


To build the rmi example

```bash
>./gradlew rmi:run
>./gradlew rmi:nativei
```

To run the rest of the examples: Execute a gradle  `runnative+[JavaFileName]`. For example

```bash
> ./gradlew runnative-examples.StaticInit
or
> ./gradlew runnative-examples.ProofOfWork -Pdebug  #enables --debug--attach
```



