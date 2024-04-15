/*
 * Copyright [2022-2024] [Xebisco]
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

package com.xebisco.yield.editor.app.editor;

import com.xebisco.yield.editor.app.Project;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Editor extends JFrame {
    public final static HashMap<String, HashMap<String, Serializable>> STD_PROJECT_VALUES = new HashMap<>();
    private boolean running;

    static {
        HashMap<String, Serializable> general = new HashMap<>();
        general.put("p_t_general_projectName", "");
        general.put("p_t_general_projectIcon", new File("icon.png"));
        STD_PROJECT_VALUES.put("p_t_general", general);
    }

    private final JTabbedPane tabbedPane = new JTabbedPane();

    private final Project project;
    private final JPanel scenePanel = new JPanel(new BorderLayout());

    private final JButton playButton, playGlobalButton, pauseButton, stopButton;

    public Editor(Project project) {
        this.project = project;
        try {
            Image loadedIcon = ImageIO.read(new File(project.path(), "icon.png")).getScaledInstance(14, 14, Image.SCALE_SMOOTH);
            BufferedImage icon = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Graphics g = icon.getGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 15, 15);
            g.drawImage(loadedIcon, 1, 1, null);
            g.setColor(Color.BLUE);
            g.drawRect(0, 0, 15, 15);
            g.dispose();
            setIconImage(icon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setTitle("Yield Editor | " + project.name());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a = JOptionPane.showConfirmDialog(Editor.this, "Save project before exiting?", "Confirm Exit", JOptionPane.YES_NO_CANCEL_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    project.saveProjectFile();
                }
                if (a == JOptionPane.YES_OPTION || a == JOptionPane.NO_OPTION) {
                    dispose();
                }
            }
        });

        setMinimumSize(new Dimension(1280, 720));


        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        fileMenu.addSeparator();
        fileMenu.add(new AbstractAction("Close") {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispatchEvent(new WindowEvent(Editor.this, WindowEvent.WINDOW_CLOSING));
            }
        });

        setJMenuBar(menuBar);


        JToolBar toolBar = new JToolBar();
        toolBar.add(Box.createHorizontalGlue());
        toolBar.setBackground(getBackground());
        toolBar.addSeparator();
        toolBar.add(playButton = new JButton(new AbstractAction("", new ImageIcon(Objects.requireNonNull(Editor.class.getResource("/icons/play.png")))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                runScene(null);
            }
        }));
        toolBar.add(playGlobalButton = new JButton(new AbstractAction("", new ImageIcon(Objects.requireNonNull(Editor.class.getResource("/icons/playglobal.png")))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                runScene(null);
            }
        }));
        toolBar.add(pauseButton = new JButton(new AbstractAction("", new ImageIcon(Objects.requireNonNull(Editor.class.getResource("/icons/pause.png")))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause();
            }
        }));
        pauseButton.setEnabled(false);

        toolBar.add(stopButton = new JButton(new AbstractAction("", new ImageIcon(Objects.requireNonNull(Editor.class.getResource("/icons/stop.png")))) {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        }));
        stopButton.setEnabled(false);
        toolBar.addSeparator();
        toolBar.add(Box.createHorizontalGlue());
        add(toolBar, BorderLayout.NORTH);


        add(tabbedPane);
        tabbedPane.addTab("Scene Panel", scenePanel);
        tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);

        //TODO actual scenes
        scenePanel.removeAll();
        scenePanel.add(new ScenePanel(new EditorScene(), project));

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void runScene(EditorScene scene) {
        running = true;
        playButton.setEnabled(false);
        playGlobalButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
        //TODO
    }

    public void pause() {
        if (running) {
            //TODO
        }
    }

    public void stop() {
        running = false;
        playButton.setEnabled(true);
        playGlobalButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        //TODO
    }

}


/*
public void mouseWheelMoved(MouseWheelEvent e) {
            Point2D before = null;
            Point2D after = null;

            AffineTransform originalTransform = new AffineTransform(at);
            AffineTransform zoomedTransform = new AffineTransform();

            try {
                before = originalTransform.inverseTransform(e.getPoint(), null);
            } catch (NoninvertibleTransformException e1) {
                e1.printStackTrace();
            }

            zoom *= 1-(e.getPreciseWheelRotation()/5);
            ((Painter) Frame1.painter).setScale(zoom/100);

            zoomedTransform.setToIdentity();
            zoomedTransform.translate(getWidth()/2, getHeight()/2);
            zoomedTransform.scale(scale, scale);
            zoomedTransform.translate(-getWidth()/2, -getHeight()/2);
            zoomedTransform.translate(translateX, translateY);

            try {
                after = zoomedTransform.inverseTransform(e.getPoint(), null);
            } catch (NoninvertibleTransformException e1) {
                e1.printStackTrace();
            }


            double deltaX = after.getX() - before.getX();
            double deltaY = after.getY() - before.getY();
            translate(deltaX,deltaY);

            Frame1.painter.repaint();
}
 */