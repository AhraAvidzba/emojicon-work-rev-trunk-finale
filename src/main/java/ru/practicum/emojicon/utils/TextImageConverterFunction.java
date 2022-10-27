package ru.practicum.emojicon.utils;

import com.googlecode.lanterna.graphics.TextImage;

import java.util.function.Function;

public interface TextImageConverterFunction extends Function<byte[], TextImage> {
}
