package lt.dvim.untappd

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.annotation.JSImport

@JSImport("recharts", JSImport.Default)
@js.native
object Recharts extends js.Object {
  type ChartData = js.Array[js.Dictionary[Int | String]]

  val LineChart: js.Object = js.native
  val BarChart: js.Object = js.native
  val Line: js.Object = js.native
  val Bar: js.Object = js.native
  val XAxis: js.Object = js.native
  val YAxis: js.Object = js.native
  val Legend: js.Object = js.native
  val Tooltip: js.Object = js.native
  val CartesianGrid: js.Object = js.native
}

@react object LineChart extends ExternalComponent {
  case class Props(width: Int, height: Int, data: Recharts.ChartData)
  override val component = Recharts.LineChart
}

@react object BarChart extends ExternalComponent {
  case class Props(width: Int, height: Int, data: Recharts.ChartData)
  override val component = Recharts.BarChart
}

@react object Line extends ExternalComponent {
  case class Props(dataKey: String, `type`: String, stroke: String, strokeWidth: Int)
  override val component = Recharts.Line
}

@react object Bar extends ExternalComponent {
  case class Props(dataKey: String, fill: String)
  override val component = Recharts.Bar
}

@react object XAxis extends ExternalComponent {
  case class Props(dataKey: String)
  override val component = Recharts.XAxis
}

@react object YAxis extends ExternalComponent {
  case class Props()
  override val component = Recharts.YAxis
}

@react object Legend extends ExternalComponent {
  case class Props(verticalAlign: String)
  override val component = Recharts.Legend
}

@react object Tooltip extends ExternalComponent {
  case class Props()
  override val component = Recharts.Tooltip
}

@react object CartesianGrid extends ExternalComponent {
  case class Props()
  override val component = Recharts.CartesianGrid
}