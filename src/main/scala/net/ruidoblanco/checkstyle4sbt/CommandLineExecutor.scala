package net.ruidoblanco.checkstyle4sbt

import sbt._

private[checkstyle4sbt] trait CommandLineExecutor {
  private[checkstyle4sbt] def executeCommandLine(commandLine: List[String], log: Logger) = try {
    log.debug(commandLine mkString "\n")
    val exitValue = Process(commandLine) ! log
    if (exitValue != 0) log.error("Nonzero exit value when attempting to call CheckStyle: " + exitValue)
  } catch {
    case e : Throwable => log.error("Exception while executing CheckStyle: %s".format(e.getMessage))
  }
}
