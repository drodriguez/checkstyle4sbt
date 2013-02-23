package net.ruidoblanco.checkstyle4sbt

object ReportFormat extends Enumeration {
  type ReportFormat = Value
  
  val Xml = Value("-fxml")
  val Plain = Value("-fplain")
}

