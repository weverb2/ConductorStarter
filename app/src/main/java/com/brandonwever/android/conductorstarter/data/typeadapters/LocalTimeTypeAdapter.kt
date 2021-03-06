package com.brandonwever.android.conductorstarter.data.typeadapters

import com.google.gson.*
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import java.lang.reflect.Type

class LocalTimeTypeAdapter : JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {
    override fun serialize(src: LocalTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(DateTimeFormatter.ISO_LOCAL_TIME.format(src))
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalTime {
        return DateTimeFormatter.ISO_LOCAL_TIME.parse(json.asString, LocalTime.FROM)
    }
}
