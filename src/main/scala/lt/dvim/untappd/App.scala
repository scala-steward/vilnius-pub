/*
 * Copyright 2019 Vilnius Pub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lt.dvim.untappd

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.util._

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("resources/logo.svg", JSImport.Default)
@js.native
object ReactLogo extends js.Object

@react class App extends StatelessComponent {
  type Props = Unit

  private val css = AppCSS

  def render() =
    div(className := "App")(
      header(className := "App-header")(
        h1(className := "App-title")("Making sense of what Vilnius is drinking")
      ),
      p(className := "App-intro")("Total daily beer check-ins"),
      CheckinsChart()
    )
}

@react class CheckinsChart extends Component {
  type Props = Unit
  case class State(data: Recharts.ChartData)

  def initialState = State(js.Array())

  def render() =
    BarChart(width = 1200, height = 300, data = state.data)(
      XAxis(dataKey = "name"),
      YAxis(),
      Legend(verticalAlign = "top"),
      Tooltip(),
      CartesianGrid(),
      Bar(dataKey = "checkins", fill = "#8884d8")
    )

  override def componentWillMount() = {
    import scala.concurrent.ExecutionContext.Implicits.global

    import sttp.client._
    implicit val backend = FetchBackend()
    val asChartData: ResponseAs[Either[String, Recharts.ChartData], Nothing] =
      asString.map(r => r.map(js.JSON.parse(_).asInstanceOf[Recharts.ChartData]))
    basicRequest.get(uri"https://api.vilnius.pub/daily").response(asChartData).send().map(_.body).onComplete {
      case Success(Right(data)) => setState(State(data))
      case Success(Left(error)) => println(error)
      case Failure(ex)          => println(ex.getMessage)
    }
  }
}
