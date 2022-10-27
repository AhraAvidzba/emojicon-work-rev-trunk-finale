package ru.practicum.emojicon.ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.input.KeyStroke;
import org.jetbrains.annotations.NotNull;
import ru.practicum.emojicon.engine.*;
import ru.practicum.emojicon.utils.TextImageUtils;

import java.net.MalformedURLException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EmojiScreenSaver implements Drawable, Controller {

    private static final String EMOJI_DATA = "data:image/gif;base64,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";

    public static final long DELAY_TIME = 60 * 1000L;

    private TextImage cachedEmojiImage;

    private Instant started = Instant.now();

    public EmojiScreenSaver() {
        try {
            cachedEmojiImage = TextImageUtils.fromDataURL(EMOJI_DATA);
        } catch (MalformedURLException e) {
            throw new RuntimeException("wrong image data url", e);
        }
    }

    @Override
    public void handleKey(KeyStroke key) {
        started = Instant.now();
    }

    public boolean isVisible() {
        return (Instant.now().toEpochMilli() - started.toEpochMilli()) >= DELAY_TIME;
    }

    @Override
    public void setSelection(UUID... objectId) {
        //not implemented
    }

    @Override
    public @NotNull List<UUID> getSelection() {
        return Collections.emptyList();
    }

    @Override
    public void drawFrame(Frame someFrame) {
        if(!isVisible()){
            return;
        }

        RootFrame frame = RootFrame.extend(someFrame);
        TerminalSize size = frame.getScreen().getTerminalSize();
        Area imageArea = new Area(0, 0, size.getColumns() - 1, size.getRows() - 1);

        PixelFrame imageFrame = new PixelFrame(frame, imageArea.getLeft(), imageArea.getTop(), imageArea.getRight(), imageArea.getBottom());
        imageFrame.drawImage(cachedEmojiImage);
    }
}
