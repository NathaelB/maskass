ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

lazy val root = (project in file("."))
  .settings(
    name := "maskass",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.1.19",

      // ZIO HTTP (REST API)
      "dev.zio" %% "zio-http" % "3.3.3",

      "io.circe" %% "circe-core" % "0.14.14",
      "io.circe" %% "circe-generic" % "0.14.14",
      "io.circe" %% "circe-parser" % "0.14.14"
    )
  )
