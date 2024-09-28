# Voyant

Kotlin Multiplatform Library

### Publish to MavenCentral

1) Registering a Sonatype account as described here: 
   https://dev.to/kotlin/how-to-build-and-publish-a-kotlin-multiplatform-library-going-public-4a8k
2) Add developer id, name, email and the project url to
   `/convention-plugins/src/main/kotlin/convention.publication.gradle.kts`
3) Add the secrets to `local.properties`:

```
signing.keyId=...
signing.password=...
signing.secretKeyRingFile=...
ossrhUsername=...
ossrhPassword=...
```

4) Run `./gradlew :voyagerX:publishAllPublicationsToSonatypeRepository`

### Build platform artifacts

#### Android aar

- Run `./gradlew :voyagerX:assembleRelease`
- Output: `/voyagerX/build/outputs/aar/voyagerX-release.aar`

#### JVM jar

- Run `./gradlew :voyagerX:jvmJar`
- Output: `/voyagerX/build/libs/voyagerX-jvm-1.0.jar`

#### iOS Framework

- Run `./gradlew :voyagerX:linkReleaseFrameworkIosArm64`
- Output: `/voyagerX/build/bin/iosArm64/releaseFramework/voyagerX.framework`

#### Wasm binary file

- Run `./gradlew :voyagerX:wasmJsBrowserDistribution`
- Output: `/voyagerX/build/dist/wasmJs/productionExecutable/voyagerX-wasm-js.wasm`
