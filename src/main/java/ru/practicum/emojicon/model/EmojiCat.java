package ru.practicum.emojicon.model;

import com.googlecode.lanterna.TextColor;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import ru.practicum.emojicon.engine.Active;
import ru.practicum.emojicon.engine.Frame;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmojiCat extends EmojiObject implements Active {

    private final List<Emoji> emojiList = Stream.of("cat", "smiley_cat", "smile_cat", "heart_eyes_cat", "kissing_cat", "smirk_cat", "scream_cat", "crying_cat_face", "joy_cat", "pouting_cat")
            .map(EmojiManager::getForAlias)
            .collect(Collectors.toList());
    int emojiIndex = 0;
    String name;
    TextColor color = getBrandNewColorOfBeneton();

    Instant dt = Instant.now();

    public EmojiCat(String name){
        this.name = name;
        this.setWidth(2);
        this.setHeight(1);
    }

    @Override
    public void drawFrame(Frame frame) {
        Emoji emoji = emojiList.get(emojiIndex);
        if(Instant.now().toEpochMilli() - dt.toEpochMilli() > 1000){
            emojiIndex++;
            if(emojiIndex == emojiList.size()){
                emojiIndex = 0;
            }
            dt = Instant.now();
        }
        frame.setPosition(0, 0);
        frame.setFillColor(null);
        frame.setColor(color);
        frame.draw(emoji);
    }

    @Override
    public void action() {
        this.color = getBrandNewColorOfBeneton();
    }

    private static TextColor getBrandNewColorOfBeneton() {
        return new TextColor.RGB(127 + (int) Math.round(Math.random() * 128), 128 - (int) Math.round(Math.random() * 127), 63 + (int) Math.round(Math.random() * 192));
    }

}
