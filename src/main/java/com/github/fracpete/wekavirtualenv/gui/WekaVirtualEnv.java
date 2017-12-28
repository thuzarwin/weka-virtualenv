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
 * WekaVirtualEnv.java
 * Copyright (C) 2017 University of Waikato, Hamilton, NZ
 */

package com.github.fracpete.wekavirtualenv.gui;

import nz.ac.waikato.cms.gui.core.BaseFrame;
import nz.ac.waikato.cms.gui.core.BasePanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Main gui for managing virtual environments for Weka.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class WekaVirtualEnv
  extends BasePanel {

  /**
   * Displays the GUI.
   *
   * @param args	ignored
   */
  public static void main(String[] args) {
    WekaVirtualEnv panel = new WekaVirtualEnv();
    BaseFrame frame = new BaseFrame("Weka virtualenv");
    frame.setDefaultCloseOperation(BaseFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(panel, BorderLayout.CENTER);
    frame.setSize(new Dimension(1024, 768));
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
