package lt.dvim.untappd

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object ReactLogo extends js.Object

@react class App extends StatelessComponent {
  type Props = Unit

  private val css = AppCSS

  def render() = {
    div(className := "App")(
      header(className := "App-header")(
        img(src := ReactLogo.asInstanceOf[String], className := "App-logo", alt := "logo"),
        h1(className := "App-title")("Welcome to React (with Scala.js!!!)")
      ),
      p(className := "App-intro")(
        "Intro paragraph"
      ),
      LineChart(width = 600, height = 300, data = js.Array(js.Dictionary("uv" -> 100), js.Dictionary("uv" -> 200), js.Dictionary("uv" -> 50)))(
        Line(`type` = "monotone", dataKey="uv", stroke="#8884d8", strokeWidth=2)
      )
    )
  }
}
