# checkstyle4sbt - Java code style checker as an SBT plugin

This SBT plugin allows you to check your Java code style using [CheckStyle](http://checkstyle.sourceforge.net/). It defines a `checkstyle` sbt action to generate the report.

## How to use

### Installing

Right now the plugin is not hosted in any Maven repository (I’m new to this things, I have to get my head around publishing artifacts).

The only way to use the plugin is having it in your local repository, compiled from source:

```shell
$ git clone https://github.com/drodriguez/checkstyle4sbt
$ cd checkstyle4sbt
$ sbt "+ publish-local"
```

### Adding checkstyle4sbt to your project

Add the following lines to your project `build.sbt` file:

```scala
import net.ruidoblanco.checkstyle4sbt.CheckStyle._

seq(checkstyleSettings : _*)
```

You also have to add the plugin dependency to your project `project/plugins.sbt` or the global `.sbt/plugins/build.sbt`:

```scala
addSbtPlugin("net.ruidoblanco" % "checkstyle4sbt" % "0.0.3")
```

### Settings

* `checkstyleVersion`
    * _Description_: Chooses the version of CheckStyle to use.
    * _Accepts_: any CheckStyle version string
    * _Default_: `"6.1.1"`
* `checkstyleReportFormat`
    * _Description_: Selects the output format for the CheckStyle report.
    * _Accepts_: `ReportFormat.{Plain, Xml}`
    * _Default_: `ReportFormat.Xml`
* `checkstyleTargetPath`
    * _Description_: Output path for the CheckStyle report.
    * _Accepts_: any directory `File`
    * _Default_: `crossTarget / "checkstyle"`
* `checkstyleReportName`
    * _Description_: Name of the report file to generate.
    * _Accepts_: any legal filename
    * _Default_: `"checkstyle.xml"`
* `checkstyleConfigurationFile`
    * _Description_: Path of the CheckStyle configuration file.
    * _Accepts_: any directory `File`
    * _Default_: `baseDirectory / "project" / "checkstyle-config.xml"`
* `checkstylePropertiesFile`
    * _Description_: Path of the CheckStyle properties file.
    * _Accepts_: `None` and `Some[File]`
    * _Default_: `None` (no property file)
* `checkstyleSourcePaths`
    * _Description_: Paths of the CheckStyle directories containing source files to analyze.
    * _Accepts_: any sequence of `File` directories
    * _Default_: `Seq(javaSource)`
* `javaExecutable`
    * _Description_: Path to the `java` executable with which to run CheckStyle.
    * _Accepts_: any string pointing to the `java` executable
    * _Default_: `java`

## TODO

- Publish in Sonatype

## Credits & Contact

checkstyle4sbt was created by [Daniel Rodríguez Troitiño](http://github.com/drodriguez). You can follow me on Twitter [@yonosoytu](http://twitter.com/yonosoytu).

checkstyle4sbt is based on the great [findbugs4sbt](https://bitbucket.org/jmhofer/findbugs4sbt) by Joachim Hofer.

## License

checkstyle4sbt is available under the MIT license. See LICENSE file for more into.