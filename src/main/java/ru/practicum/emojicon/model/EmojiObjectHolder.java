package ru.practicum.emojicon.model;

import ru.practicum.emojicon.engine.Point;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

public interface EmojiObjectHolder {

    UUID addObject(EmojiObject obj, Point position);

    default boolean isFreeArea(int left, int top, int right, int bottom) {
        return isFreeArea(left, top, right, bottom, Collections.emptySet());
    };

    boolean isFreeArea(int left, int top, int right, int bottom, Set<UUID> ignoreObjects);

}
