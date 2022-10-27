package ru.practicum.emojicon.utils;

import java.util.stream.Stream;

public enum TextImageConverter {
    STRING("text/plain", new TextImageFromString()),
    GIF("image/gif", new TextImageFromGif());

    private final String mimetype;
    private final TextImageConverterFunction converterFunction;

    TextImageConverter(String mimetype, TextImageConverterFunction converterFunction) {
        this.mimetype = mimetype;
        this.converterFunction = converterFunction;
    }

    public String getMimetype() {
        return mimetype;
    }

    public TextImageConverterFunction getConverterFunction() {
        return converterFunction;
    }

    public static TextImageConverter forMimetype(String mimetype) {
        return Stream.of(values()).filter(c -> c.mimetype.equalsIgnoreCase(mimetype)).findFirst().orElseThrow(() -> new NoSuchConverterException(String.format("not found converter for %s", mimetype)));
    }
}
