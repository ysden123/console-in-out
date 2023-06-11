/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.console

import com.typesafe.scalalogging.StrictLogging

import scala.concurrent.{ExecutionContext, Future}
import scala.swing.*
import scala.swing.{Frame, MainFrame, SimpleSwingApplication}

object Main extends SimpleSwingApplication with StrictLogging:
  given ec: ExecutionContext = scala.concurrent.ExecutionContext.global

  override def top: Frame = new MainFrame {
    val consolePanel = new ConsolePanel(inputHandler)
    contents = consolePanel

    consolePanel.println("Test line")

    title = "Console in/out"
    size = new Dimension(400, 400)
    centerOnScreen()

    consolePanel.println("Test line 2")

    process(consolePanel)

    private def inputHandler(text: String): Unit =
      consolePanel.println(text)
  }

  private def process(consolePanel: ConsolePanel): Unit =
    consolePanel.println("Test line 3")

    Future {
      Thread.sleep(2000)
      consolePanel.println("Test line 4")
      Thread.sleep(2000)
      consolePanel.println("Test line 5")

      Thread.sleep(2000)
      consolePanel.print("a")

      Thread.sleep(500)
      consolePanel.print("b")

      Thread.sleep(500)
      consolePanel.print("c")

      consolePanel.println("")
      consolePanel.println("Finished")
    }
