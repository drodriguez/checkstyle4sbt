package net.ruidoblanco.checkstyle4sbt

import sbt._
import sbt.Keys.TaskStreams

object CheckStyle extends Plugin
  with Settings with CommandLine with CommandLineExecutor {

  override def checkstyleTask(commandLine: List[String], streams: TaskStreams): Unit = {
    streams.log.debug("CheckStyle command line to execute: \"%s\"" format (commandLine mkString " "))

    executeCommandLine(commandLine, streams.log)
  }
}
