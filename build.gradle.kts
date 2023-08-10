//Fabric Properties
//Check these on https://modmuss50.me/fabric.html
val minecraftVersion = "1.20.1"
val yarnMappingsVersion = "1.20.1+build.10"
val fabricLoaderVersion = "0.14.22"
//Fabric api
val fabricApiVersion = "0.86.1+1.20.1"

//Mod Properties
val modVersion = "1.3.0"
val mavenGroupId = "ru.maxvar"
val archivesBaseName = "mcf-magnets"

//Kotlin and fabric-language-kotlin
val kotlinVersion = "1.8.10"
val fabricKotlinVersion = "1.9.2+kotlin.1.8.10" //https://www.curseforge.com/minecraft/mc-mods/fabric-language-kotlin
//Mod Dependencies
val fabricModMenuVersion = "7.2.1" //https://modrinth.com/mod/modmenu/versions?l=fabric
val fabricClothConfigVersion = "11.1.106" //https://modrinth.com/mod/cloth-config/versions?l=fabric

plugins {
    java
    kotlin("jvm") version "1.8.10" //match with above kotlinVersion
    kotlin("plugin.serialization") version "1.8.10" //match with above kotlinVersion
    id("fabric-loom") version "1.1-SNAPSHOT"
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

    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.compileJava {
    sourceCompatibility = "17"
    targetCompatibility = "17"
    options.encoding = "UTF-8"

}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.processResources {
    filesMatching("**/fabric.mod.json") {
        println("$sourcePath:$sourceName")
        expand(
            "version" to modVersion,
            "minecraftVersion" to minecraftVersion,
            "fabricLoaderVersion" to fabricLoaderVersion,
            "fabricKotlinVersion" to fabricKotlinVersion
        )
    }
}

tasks.jar {
    archiveVersion.set("$modVersion-$minecraftVersion")
}

tasks.remapJar {
    archiveVersion.set("$modVersion-$minecraftVersion")
}
