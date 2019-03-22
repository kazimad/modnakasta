package test.org.kastatest.data.facilities

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.JsonSyntaxException

import java.lang.reflect.Type
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class RestDateTypeAdapter(datePattern: String) : JsonSerializer<Date>, JsonDeserializer<Date> {

    private val mDateFormat: DateFormat

    init {
        mDateFormat = SimpleDateFormat(datePattern, Locale.US)
        mDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    }

    override fun serialize(src: Date, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        synchronized(mDateFormat) {
            val dateFormatAsString = mDateFormat.format(src)
            return JsonPrimitive(dateFormatAsString)
        }
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type, context: JsonDeserializationContext): Date {
        if (json == null || !json.isJsonPrimitive || !(json as JsonPrimitive).isString) {
            throw JsonParseException("Expected date as string")
        }
        synchronized(mDateFormat) {
            try {
                return mDateFormat.parse(json.asString)
            } catch (e: ParseException) {
                throw JsonSyntaxException(json.asString, e)
            }

        }
    }
}
