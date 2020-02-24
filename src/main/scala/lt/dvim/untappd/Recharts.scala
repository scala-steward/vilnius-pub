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
import scala.scalajs.js.|

import slinky.core.ExternalComponent
import slinky.core.annotations.react

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
