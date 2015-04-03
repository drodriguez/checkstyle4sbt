package net.ruidoblanco.checkstyle4sbt

import sbt._
import sbt.Keys._

import java.io.File

private[checkstyle4sbt] trait CommandLine extends Plugin with Settings {

  override def checkstyleCommandLineTask(
    checkstyleVersion: String,
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

      val recursiveOption = {
        def before6_3(major: Int, minor: Int) =
          major.toInt < 6 || (major.toInt == 6 && minor.toInt < 3)

        val versionPattern = """^(\d+)\.(\d+).*""".r
        checkstyleVersion match {
          case versionPattern(major, minor) if before6_3(major.toInt, minor.toInt) => Some("-r")
          case _ => None
        }
      }

      addPropertiesFileParameter(
        List(
          "-c", paths.configurationFile.toString,
          misc.reportFormat.toString,
          "-o", reportFile.toString
        ) ++ recursiveOption ++ paths.sourcePaths.map(_.absolutePath)
      )
    }

    def addPropertiesFileParameter(arguments: List[String]) = paths.propertiesFile match {
      case None => arguments
      case Some(propertiesFile) => arguments ++ List("-p", propertiesFile.toString)
    }

    def commandLineClasspath(classpathFiles: Seq[File]) = PathFinder(classpathFiles).absString

    streams.log.debug("checkstyleCommandLine task executed")
    streams.log.debug(paths.targetPath.toString)
    streams.log.debug(paths.sourcePaths.mkString(", "))
    IO.createDirectory(paths.targetPath)

    checkstyleCommandLine
  }
}
