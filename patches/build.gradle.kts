
group = "app.template"

patches {
    about {
        name = "Messenger Liquid Glass"
        description = "Liquid Glass interface customization for Messenger Android"
        source = "https://github.com/isaiastdryt-collab/messenger-liquid-glass.git"
        author = "isaiastdryt-collab"
        contact = "na"
        website = "https://github.com/isaiastdryt-collab/messenger-liquid-glass"
        license = "GPLv3"
    }
}

val patchListGeneratorClasspath =
    configurations.create("patchListGeneratorClasspath")

dependencies {
    compileOnly(libs.gson)
    patchListGeneratorClasspath(libs.gson)
}

tasks {
    register<JavaExec>("generatePatchesList") {
        description = "Build patch with patch list"

        dependsOn(build)

        classpath = sourceSets["main"].runtimeClasspath +
            patchListGeneratorClasspath

        mainClass.set("util.PatchListGeneratorKt")
    }

    publish {
        dependsOn("generatePatchesList")
    }
}
