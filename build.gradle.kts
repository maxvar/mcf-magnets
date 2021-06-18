//Fabric Properties
//Check these on https://modmuss50.me/fabric.html
val minecraftVersion = "1.17"
val yarnMappingsVersion = "1.17+build.13"
val fabricLoaderVersion = "0.11.6"
//Fabric api
val fabricApiVersion = "0.35.2+1.17"
val loomVersion = "0.6-SNAPSHOT"

//Mod Properties
val modVersion = "1.1.0"
val mavenGroupId = "ru.maxvar"
val archivesBaseName = "mcf-magnets"

//Kotlin and fabric-language-kotlin
val kotlinVersion = "1.5.10"
val fabricKotlinVersion = "1.6.1+kotlin.1.5.10"
//Mod Dependencies
val fabricModMenuVersion = "2.0.2"
val fabricClothConfigVersion = "5.0.34"

plugins {
    java
    kotlin("jvm") version "1.5.10" //match with above kotlinVersion
    kotlin("plugin.serialization") version "1.5.10" //match with above kotlinVersion
    id("fabric-loom") version "0.6-SNAPSHOT"
}

repositories {
    maven(url = "https://maven.fabricmc.net/") { name = "Fabric MC" }
    maven(url = "https://maven.shedaniel.me/") { name = "shedaniel" }
    maven(url = "https://maven.terraformersmc.com") { name = "modmenu" }
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarnMappingsVersion:v2")
    modImplementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabricKotlinVersion")
    // ModMenu api
    modImplementation("com.terraformersmc:modmenu:$fabricModMenuVersion")
    // Cloth-config api + bundle this library in assembly
    modApi("me.shedaniel.cloth:cloth-config-fabric:$fabricClothConfigVersion") {
        exclude(group = "net.fabricmc.fabric-api")
    }
//    include("me.shedaniel.cloth:cloth-config-fabric:$fabricClothConfigVersion")

    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")
}

java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.processResources {
    filesMatching("**/fabric.mod.json") {
        println("$sourcePath:$sourceName")
        expand("version" to modVersion)
    }
}

tasks.jar {
    archiveVersion.set("$modVersion-$minecraftVersion")
}

tasks.remapJar {
    archiveVersion.set("$modVersion-$minecraftVersion")
}
