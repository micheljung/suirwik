import com.jfrog.bintray.gradle.BintrayExtension
import org.jetbrains.dokka.gradle.DokkaTask

group = "io.github.micheljung"
version = "$version"
description = "Semantic UI React Wrapper written in Kotlin"

plugins {
  signing
  `maven-publish`
  id("com.jfrog.bintray") version "1.8.5"
  id("org.jetbrains.dokka") version "1.4.20"
  id("org.jetbrains.kotlin.js") version "1.4.20"
  id("com.github.node-gradle.node") version "3.0.1"
}

repositories {
  maven("http://dl.bintray.com/kotlin/kotlin-js-wrappers")
  mavenCentral()
  jcenter()
}

val dokkaOutputDir = "$buildDir/dokka"

tasks.getByName<DokkaTask>("dokkaHtml") {
  outputDirectory.set(file(dokkaOutputDir))
}

val deleteDokkaOutputDir by tasks.register<Delete>("deleteDokkaOutputDirectory") {
  delete(dokkaOutputDir)
}

val javadocJar = tasks.register<Jar>("javadocJar") {
  dependsOn(deleteDokkaOutputDir, tasks.dokkaHtml)
  archiveClassifier.set("javadoc")
  from(dokkaOutputDir)
}

dependencies {
  val kotlinVersion = "1.4.20"
  val kotlinJsVersion = "pre.129-kotlin-$kotlinVersion"
  val kotlinReactVersion = "17.0.0-$kotlinJsVersion"

  implementation(kotlin("stdlib-js"))
  implementation("org.jetbrains", "kotlin-react", kotlinReactVersion)
  implementation("org.jetbrains", "kotlin-react-dom", kotlinReactVersion)

  testImplementation(kotlin("test-js"))
}

kotlin {
  js {
    browser {
      webpackTask {
        cssSupport.enabled = true
      }

      runTask {
        cssSupport.enabled = true
      }

      testTask {
        useKarma {
          useChromeHeadless()
          webpackConfig.cssSupport.enabled = true
        }
      }
    }
    binaries.executable()
  }
}

val publicationName = "kotlin"

bintray {
  user = System.getenv("BINTRAY_USER")
  key = System.getenv("BINTRAY_KEY")
  setPublications(publicationName)
  pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
    repo = "maven"
    name = "suirwik"
    setLicenses("GPL-3.0")

    vcsUrl = "https://github.com/micheljung/suirwik.git"
    githubRepo = "micheljung/suirwik"
    publicDownloadNumbers = true
  })
}

publishing {
  repositories {
    maven {
      name = "OSSRH"
      setUrl("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
      credentials {
        username = System.getenv("OSSRH_USERNAME")
        password = System.getenv("OSSRH_TOKEN")
      }
    }
  }

  publications {
    create<MavenPublication>(publicationName) {
      groupId = "io.github.micheljung"
      artifactId = "suirwik"

      from(components["kotlin"])
//      artifact(tasks["KDocJar"])
      artifact(tasks["javadocJar"])

      pom {
        name.set("Suirwik Components")
        description.set(project.description)
        url.set("https://github.com/micheljung/suirwik")
        licenses {
          license {
            name.set("GNU General Public License, Version 3.0")
            url.set("https://www.gnu.org/licenses/gpl-3.0.html")
          }
        }
        developers {
          developer {
            id.set("mj")
            name.set("Michel Jung")
            email.set("michel.jung89@gmail.com")
          }
        }
        scm {
          connection.set("scm:git:https://github.com/micheljung/suirwik.git")
          developerConnection.set("scm:git:https://github.com/micheljung/suirwik.git")
          url.set("https://github.com/micheljung/suirwik")
        }
      }
    }
  }
}

signing {
  useGpgCmd()
  sign(publishing.publications[publicationName])
}
