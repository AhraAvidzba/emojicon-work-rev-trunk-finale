package ru.practicum.emojicon.utils;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class TextImageFromGif implements TextImageConverterFunction {

    @Override
    public TextImage apply(byte[] bytes) {
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
            BasicTextImage ret = new BasicTextImage(new TerminalSize(image.getWidth(), image.getHeight()));
            for(int x = 0; x < image.getWidth(); x++){
                for (int y = 0; y < image.getHeight(); y++){
                    Color color = new Color(image.getRGB(x, y));
                    ret.setCharacterAt(x, y, TextCharacter.DEFAULT_CHARACTER.withCharacter(' ').withForegroundColor(new TextColor.RGB(color.getRed(), color.getGreen(), color.getBlue())));
                }
            }
            return ret;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
