package com.xebisco.yield;

import com.xebisco.yield.editoruse.VisibleOnInspector;

public class Text extends ComponentBehavior {
    private final DrawInstruction drawInstruction = new DrawInstruction();
    @VisibleOnInspector
    private Color color = Colors.LIGHT_BLUE.brighter();
    @VisibleOnInspector
    private String contents = "";
    @VisibleOnInspector
    private Font font = Global.getDefaultFont();

    private double width, height;

    public Text() {
    }

    public Text(String contents) {
        this.contents = contents;
    }

    @Override
    public void onStart() {
        drawInstruction.setSize(new Size2D(0, 0));
        drawInstruction.setType(DrawInstruction.Type.TEXT);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(PlatformGraphics graphics) {
        if (font != null) {
            drawInstruction.setRenderRef(contents);
            drawInstruction.setFont(font);
            drawInstruction.setRotation(getEntity().getTransform().getzRotation());
            drawInstruction.setInnerColor(color);
            drawInstruction.setPosition(getEntity().getTransform().getPosition());
            width = ((FontLoader) graphics).getStringWidth(contents, font.getFontRef());
            height = ((FontLoader) graphics).getStringHeight(contents, font.getFontRef());
            if (drawInstruction.getSize() != null) {
                drawInstruction.getSize().set(width, height);
                graphics.draw(drawInstruction);
            }
        }
    }

    public DrawInstruction getDrawInstruction() {
        return drawInstruction;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
