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

import com.github.fracpete.wekavirtualenv.gui.core.IconHelper;
import com.github.fracpete.wekavirtualenv.gui.env.EnvironmentsPanel;
import nz.ac.waikato.cms.gui.core.BaseFrame;
import nz.ac.waikato.cms.gui.core.BasePanel;

import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Main gui for managing virtual environments for Weka.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class WekaVirtualEnv
  extends BasePanel {

  /** the divider panel. */
  protected JSplitPane m_SplitPane;

  /** the environments. */
  protected EnvironmentsPanel m_PanelEnvs;

  /** the tabbed pane for the outputs. */
  protected JTabbedPane m_TabbedPaneOutputs;

  /**
   * Initializes the widgets.
   */
  @Override
  protected void initGUI() {
    super.initGUI();

    setLayout(new BorderLayout());

    m_SplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    m_SplitPane.setResizeWeight(0.0);
    add(m_SplitPane);

    m_TabbedPaneOutputs = new JTabbedPane();
    m_SplitPane.setRightComponent(m_TabbedPaneOutputs);

    m_PanelEnvs = new EnvironmentsPanel();
    m_PanelEnvs.setTabbedPane(m_TabbedPaneOutputs);
    m_SplitPane.setLeftComponent(m_PanelEnvs);
  }

  /**
   * Returns the menu bar to use.
   *
   * @return		the menu bar
   */
  public JMenuBar getMenuBar() {
    // TODO
    return null;
  }

  /**
   * Displays the GUI.
   *
   * @param args	ignored
   */
  public static void main(String[] args) {
    WekaVirtualEnv panel = new WekaVirtualEnv();
    BaseFrame frame = new BaseFrame("Weka virtualenv");
    frame.setIconImage(IconHelper.getIcon("wenv").getImage());
    frame.setDefaultCloseOperation(BaseFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(panel, BorderLayout.CENTER);
    frame.setJMenuBar(panel.getMenuBar());
    frame.setSize(new Dimension(1024, 768));
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
