package com.brandonwever.android.conductorstarter.data.typeadapters

import com.brandonwever.android.conductorstarter.util.DateUtil
import com.google.gson.*
import org.threeten.bp.ZonedDateTime
import java.lang.reflect.Type

class ZonedDateTimeTypeAdapter : JsonDeserializer<ZonedDateTime>, JsonSerializer<ZonedDateTime> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ZonedDateTime {
        return ZonedDateTime.parse(json.asString)
    }

    override fun serialize(src: ZonedDateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(DateUtil().toISO(src))
    }
}