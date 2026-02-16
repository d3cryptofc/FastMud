plugins {
    java
}

group = properties["projectGroup"] as String
version = properties["projectVersion"] as String

repositories {
    mavenCentral()

    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")

        content {
            includeGroup("org.bukkit")
            includeGroup("org.spigotmc")
        }
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:${properties["gameVersion"]}-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks {
    processResources {
        expand(
            "projectVersion" to project.version,
            "projectName" to project.name,
            "projectGroup" to project.group,
            "projectAuthor" to project.property("projectAuthor").toString(),
            "gameVersion" to project.property("gameVersion").toString()
        )
    }
}
