package com.xebisco.yield.editor.app;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.io.IOException;
import java.util.Locale;

public class Entry {
    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Locale.setDefault(Locale.US);
        System.setProperty("sun.java2d.opengl", "True");
        Srd.LANG.load(Entry.class.getResourceAsStream("langs/en.properties"));

        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

        FlatDarculaLaf.setup();

        EditorWindow window = new EditorWindow();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}