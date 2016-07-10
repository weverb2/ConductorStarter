package com.brandonwever.android.conductorstarter.data.typeadapters

import com.brandonwever.android.conductorstarter.util.DateUtil
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import org.threeten.bp.LocalDate
import java.lang.reflect.Type

class LocalDateTypeAdapter : JsonDeserializer<LocalDate>, JsonSerializer<LocalDate> {
  override fun deserialize(json: JsonElement, typeOfT: Type,
      context: JsonDeserializationContext): LocalDate {
    return LocalDate.parse(json.asString)
  }

  override fun serialize(src: LocalDate, typeOfSrc: Type,
      context: JsonSerializationContext): JsonElement {
    return JsonPrimitive(DateUtil().toDate(src))
  }
}
