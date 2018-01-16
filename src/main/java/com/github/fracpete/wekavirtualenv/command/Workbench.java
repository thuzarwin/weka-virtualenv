/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Workbench.java
 * Copyright (C) 2018 University of Waikato, Hamilton, NZ
 */

package com.github.fracpete.wekavirtualenv.command;

import com.github.fracpete.simpleargparse4j.Namespace;
import com.github.fracpete.wekavirtualenv.env.Environment;

/**
 * Launches the Weka Workbench.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class Workbench
  extends AbstractLaunchCommand {

  public static final String CLASSNAME = "weka.gui.Workbench";

  /**
   * The name of the command (used on the commandline).
   *
   * @return		the name
   */
  @Override
  public String getName() {
    return "workbench";
  }

  /**
   * Returns a short help string.
   *
   * @return		the help string
   */
  public String getHelp() {
    return "Launches the Weka Workbench.";
  }

  /**
   * Returns whether the action is available.
   *
   * @return		true if available
   */
  public boolean isAvailable() {
    return Environment.hasClass(getEnv().weka, CLASSNAME, true);
  }

  /**
   * Executes the command.
   *
   * @param ns		the namespace of the parsed options, null if no options to parse
   * @param options	additional command-line options
   * @return		true if successful
   */
  protected boolean doExecute(Namespace ns, String[] options) {
    if (!isAvailable()) {
      addError("Workbench is not available in Weka " + getEnv().version() + " (" + CLASSNAME + ")!");
      return false;
    }

    return launch(build(CLASSNAME, options));
  }
}