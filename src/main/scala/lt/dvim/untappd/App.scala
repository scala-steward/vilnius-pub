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
      BarChart(width = 600, height = 300, data = js.Array(js.Dictionary("name" -> "01-05", "checkins" -> 100), js.Dictionary("name" -> "01-06", "checkins" -> 200), js.Dictionary("name" -> "01-07", "checkins" -> 50)))(
        XAxis(dataKey="name"),
        YAxis(),
        Legend(),
        Tooltip(),
        CartesianGrid(),
        Bar(dataKey="checkins", fill="#8884d8")
      )
    )
  }
}
