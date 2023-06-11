/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.console

import com.typesafe.scalalogging.LazyLogging

import scala.swing.BorderPanel.Position
import scala.swing.event.{Key, KeyPressed}
import scala.swing.{BorderPanel, Frame, Panel, ScrollPane, TextArea, TextField}

class ConsolePanel(inputHandler: (text: String) => Unit) extends BorderPanel with LazyLogging:
  private val output = new TextArea()
  private val scrollPane = new ScrollPane {
    contents = output
  }
  private val input = new TextField {
    listenTo(keys)
    reactions += {
      case KeyPressed(_, key, _, _) =>
        if key == Key.Enter then
          inputHandler(this.text)
      case _ =>
    }
  }


  layout(input) = Position.South
  layout(scrollPane) = Position.Center

  def print(line: String): Unit =
    output.append(line)

  def println(line: String): Unit =
    print(line + "\n")
