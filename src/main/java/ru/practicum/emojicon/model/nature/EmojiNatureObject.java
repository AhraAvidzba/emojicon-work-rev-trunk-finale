package ru.practicum.emojicon.model.nature;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import ru.practicum.emojicon.engine.Frame;
import ru.practicum.emojicon.engine.Point;
import ru.practicum.emojicon.model.EmojiObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;

public class EmojiNatureObject extends EmojiObject {
    private static final String FILL_0_4 = " ";
    private static final String FILL_1_4 = "░";
    private static final String FILL_2_4 = "▒";
    private static final String FILL_3_4 = "▓";

    private static final List<String> FILL_RANGE = Arrays.asList(FILL_0_4, FILL_1_4, FILL_2_4, FILL_3_4);

    List<List<TextCharacter>> objShape = new ArrayList<>();

    public EmojiNatureObject(int width, int height) {
        super(width, height);
        fillRecursive(new Point((int) round(random() * (width - 1)), (int) round(random() * (height - 1))));
    }

    private void fillRecursive(Point pt) {
        while (objShape.size() <= pt.getX()){
            objShape.add(new ArrayList<>());
        }
        List<TextCharacter> col = objShape.get(pt.getX());
        while (col.size() <= pt.getY()){
            col.add(null);
        }
        Point nextPt;
        if(col.get(pt.getY()) == null){
            int nextCharIdx = (int) round(random() * 3);
            String nextChar = FILL_RANGE.get(nextCharIdx);
            col.set(pt.getY(), TextCharacter.DEFAULT_CHARACTER.withForegroundColor(TextColor.ANSI.BLACK_BRIGHT).withCharacter(nextChar.charAt(0)));
            switch (nextChar){
                case FILL_0_4:
                    nextPt = pt.move(1, 0);
                    break;
                case FILL_1_4:
                    nextPt = pt.move(1, 1);
                    break;
                case FILL_2_4:
                    nextPt = pt.move(-1, 0);
                    break;
                case FILL_3_4:
                    nextPt = pt.move(-1, -1);
                    break;
                default:
                    throw new RuntimeException("strange char "+nextChar);
            }
            if(nextPt.getX() < 0){
                nextPt.setX(getWidth());
            } else if(nextPt.getX() > getWidth()){
                nextPt.setX(0);
            }
            if(nextPt.getY() < 0){
                nextPt.setY(getHeight());
            } else if(nextPt.getY() > getHeight()){
                nextPt.setY(0);
            }
        } else {
            nextPt = nextFreePoint();
        }
        if(nextPt != null){
            fillRecursive(nextPt);
        }
    }

    private Point nextFreePoint() {
        for (int x = 0; x < getWidth(); x++){
            List<TextCharacter> column = x < objShape.size() ? objShape.get(x) : null;
            if(column != null){
                for(int y = 0; y < getHeight(); y++){
                    if(y >= column.size() || column.get(y) == null){
                        return new Point(x, y);
                    }
                }
            } else {
                return new Point(x, 0);
            }
        }
        return null;
    }


    @Override
    public void drawFrame(Frame frame) {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                TextCharacter ch = objShape.get(x).get(y);
                frame.setPosition(x, y);
                frame.setFillColor(null);
                frame.setColor(ch.getForegroundColor());
                frame.draw(ch.getCharacterString().charAt(0));
            }
        }
    }
}
