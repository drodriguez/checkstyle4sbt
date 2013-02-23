package net.ruidoblanco.checkstyle4sbt

import sbt._
import sbt.Keys._

import java.io.File

import ReportFormat._

private[checkstyle4sbt] trait CommandLine extends Plugin with Settings {

  override def checkstyleCommandLineTask(
    checkstyleClasspath: Classpath,
    paths: PathSettings,
    misc: MiscSettings,
    streams: TaskStreams) = {

    def checkstyleCommandLine = checkstyleJavaCall ++ checkstyleCallOptions

    def checkstyleJavaCall = {
      val classpath = commandLineClasspath(checkstyleClasspath.files)
      streams.log.debug("CheckStyle classpath: %s" format classpath)

      List("java", "-cp", classpath, "com.puppycrawl.tools.checkstyle.Main")
    }

    def checkstyleCallOptions = {
      val reportFile = paths.targetPath / paths.reportName

      addPropertiesFileParameter(
        List(
          "-c", paths.configurationFile.toString,
          misc.reportFormat.toString,
          "-o", reportFile.toString,
          "-r",  paths.sourcePath.toString
        )
      )
    }

    def addPropertiesFileParameter(arguments: List[String]) = paths.propertiesFile match {
      case None => arguments
      case Some(propertiesFile) => arguments ++ List("-p", propertiesFile.toString)
    }

    def commandLineClasspath(classpathFiles: Seq[File]) = PathFinder(classpathFiles).absString

    streams.log.debug("checkstyleCommandLine task executed")
    streams.log.debug(paths.targetPath.toString)
    streams.log.debug(paths.sourcePath.toString)
    IO.createDirectory(paths.targetPath)

    checkstyleCommandLine
  }
}
