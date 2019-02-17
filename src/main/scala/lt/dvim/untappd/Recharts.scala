package lt.dvim.untappd

import slinky.core.ExternalComponent
import slinky.core.annotations.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("recharts", JSImport.Default)
@js.native
object Recharts extends js.Object {
  val LineChart: js.Object = js.native
  val Line: js.Object = js.native
}

@react object LineChart extends ExternalComponent {
  case class Props(width: Int, height: Int/*, data: js.Array[js.Object]*/)
  override val component = Recharts.LineChart
}

@react object Line extends ExternalComponent {
  case class Props(`type`: String, dataKey: String, stroke: String, strokeWidth: Int)
  override val component = Recharts.Line
}
