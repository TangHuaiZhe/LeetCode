plugins {
  `java-library`
  kotlin("jvm") version "1.5.21"
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(8))
  }
}

version = "1.2.1"

sourceSets {
  main {
    java {
      setSrcDirs(listOf("leetcode", "jzoffer", "interview","dynamicProgramming","codility"))
    }
  }
}



repositories {
  mavenCentral()
}

dependencies {
}
