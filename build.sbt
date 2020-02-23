enablePlugins(ScalaJSBundlerPlugin)

name := "vilnius-pub"

scalaVersion := "2.13.1"

Compile / npmDependencies += "react" -> "16.7.0"
Compile / npmDependencies += "react-dom" -> "16.7.0"
Compile / npmDependencies += "react-proxy" -> "1.1.8"
Compile / npmDependencies += "recharts" -> "1.8.5"

Compile / npmDevDependencies += "file-loader" -> "3.0.1"
Compile / npmDevDependencies += "style-loader" -> "0.23.1"
Compile / npmDevDependencies += "css-loader" -> "2.1.0"
Compile / npmDevDependencies += "html-webpack-plugin" -> "3.2.0"
Compile / npmDevDependencies += "copy-webpack-plugin" -> "4.6.0"
Compile / npmDevDependencies += "webpack-merge" -> "4.2.1"

val Slinky = "0.6.3"

libraryDependencies ++= Seq(
  "me.shadaj"             %%% "slinky-web" % Slinky,
  "me.shadaj"             %%% "slinky-hot" % Slinky,
  "com.softwaremill.sttp" %%% "core"       % "1.7.2",
  "org.scalatest"         %%% "scalatest"  % "3.1.1" % "test"
)

scalacOptions ++= Seq(
  "-P:scalajs:sjsDefinedByDefault",
  "-Ymacro-annotations",
)

webpack / version := "4.29.0"
startWebpackDevServer / version := "3.1.14"

webpackResources := baseDirectory.value / "webpack" * "*"

fastOptJS / webpackConfigFile := Some(baseDirectory.value / "webpack" / "webpack-fastopt.config.js")
fullOptJS / webpackConfigFile := Some(baseDirectory.value / "webpack" / "webpack-opt.config.js")
Test / webpackConfigFile := Some(baseDirectory.value / "webpack" / "webpack-core.config.js")

fastOptJS / webpackDevServerExtraArgs := Seq("--inline", "--hot")
fastOptJS / webpackBundlingMode := BundlingMode.LibraryOnly()

Test / requireJsDomEnv := true

addCommandAlias("dev", ";fastOptJS::startWebpackDevServer;~fastOptJS")
addCommandAlias("build", "fullOptJS::webpack")
