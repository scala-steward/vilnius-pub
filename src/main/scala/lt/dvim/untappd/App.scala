package lt.dvim.untappd

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}

import scala.util._

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
      CheckinsChart()
    )
  }
}

@react class CheckinsChart extends Component {
  type Props = Unit
  case class State(data: Recharts.ChartData)

  def initialState = State(js.Array())

  def render() =
    BarChart(width = 1200, height = 300, data = state.data)(
      XAxis(dataKey="name"),
      YAxis(),
      Legend(verticalAlign = "top"),
      Tooltip(),
      CartesianGrid(),
      Bar(dataKey="checkins", fill="#8884d8")
    )

  override def componentWillMount() = {
    import com.softwaremill.sttp._
    import scala.concurrent.ExecutionContext.Implicits.global
    implicit val backend = FetchBackend()
    val asChartData: ResponseAs[Recharts.ChartData, Nothing] = asString.map(js.JSON.parse(_).asInstanceOf[Recharts.ChartData])
    sttp.get(uri"http://www.mocky.io/v2/5c692d06370000c80507fcf2").response(asChartData).send().onComplete {
      case Success(resp) => setState(State(resp.unsafeBody))
      case Failure(ex) => println(ex.getMessage)
    }
  }
}
