import net.fabricmc.loom.task.RemapJarTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//Fabric Properties
//Check these on https://modmuss50.me/fabric.html
val minecraftVersion = "1.15.2"
val yarnMappingsVersion = "1.15.2+build.15"
val fabricLoaderVersion = "0.8.2+build.194"
//Fabric api
val fabricApiVersion = "0.5.1+build.294-1.15"
val loomVersion = "0.2.7-SNAPSHOT"

//Mod Properties
val modVersion = "1.1.0"
val mavenGroupId = "ru.maxvar"
val archivesBaseName = "mcf-magnets"

//Kotlin and fabric-language-kotlin
val kotlinVersion = "1.3.61"
val fabricKotlinVersion = "1.3.61+build.1"
//Mod Dependencies
val fabricModMenuVersion = "1.10.2+build.35"
val fabricClothConfigVersion = "2.5.4"

plugins {
    java
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.serialization") version "1.3.61"
    id("fabric-loom") version "0.2.7-SNAPSHOT"
}

repositories {
    maven(url = "https://maven.fabricmc.net/") { name = "Fabric MC" }
    jcenter()
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$yarnMappingsVersion:v2")
    modImplementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabricKotlinVersion")
    // ModMenu api
    modImplementation("io.github.prospector:modmenu:$fabricModMenuVersion")
    // Cloth-config api + bundle this library in assembly
    modImplementation("me.shedaniel.cloth:config-2:$fabricClothConfigVersion")
    include("me.shedaniel.cloth:config-2:$fabricClothConfigVersion")

    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType<Jar> {
    archiveVersion.set("$modVersion-$minecraftVersion")
}

tasks.withType<RemapJarTask> {
    archiveVersion.set("$modVersion-$minecraftVersion")
}

//processResources {

//    inputs.property "version", project.version
//
//    from(sourceSets.main.resources.srcDirs) {
//        include "fabric.mod.json"
//        expand "version": project.version
//    }
//
//    from(sourceSets.main.resources.srcDirs) {
//        exclude "fabric.mod.json"
//    }
//}
//
//
