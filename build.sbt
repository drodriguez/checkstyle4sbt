sbtPlugin := true

name := "checkstyle4sbt"

organization := "net.ruidoblanco"

version := "0.0.2"

scalaVersion := "2.10.3"

scalacOptions ++= Seq("-unchecked", "-deprecation")

crossScalaVersions := Seq("2.9.2", "2.10.3")
