plugins {
  `java-library`
  kotlin("jvm") version "1.5.21"
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(8))
  }
}

sourceSets {
  main {
    java {
      setSrcDirs(listOf("leetcode", "jzoffer", "interview", "codility"))
    }
  }
}

repositories {
  mavenCentral()
}

dependencies {
}
