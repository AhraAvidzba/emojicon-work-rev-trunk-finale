package ru.practicum.emojicon.utils;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextImage;
import eu.maxschuster.dataurl.DataUrl;
import eu.maxschuster.dataurl.DataUrlSerializer;
import eu.maxschuster.dataurl.IDataUrlSerializer;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class TextImageUtils {

    public static TextImage fromString(String text){
        return TextImageConverter.forMimetype("text/plain").getConverterFunction().apply(text.getBytes(StandardCharsets.UTF_8));
    }

    public static TextImage fromDataURL(String dataUrl) throws MalformedURLException {
        IDataUrlSerializer serializer = new DataUrlSerializer();
        DataUrl unserialized = serializer.unserialize(dataUrl);
        return TextImageConverter.forMimetype(unserialized.getMimeType()).getConverterFunction().apply(unserialized.getData());
    }
}
