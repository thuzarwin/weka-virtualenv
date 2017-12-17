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
 * HelpRequestedException.java
 * Copyright (C) 2017 University of Waikato, Hamilton, NZ
 */

package com.github.fracpete.wekavirtualenv.parser;

/**
 * Gets thrown when then help flag is used: --help
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class HelpRequestedException
  extends ArgumentParserException {

  /**
   * Initializes the exception.
   */
  public HelpRequestedException() {
    super("Help requested");
  }
}