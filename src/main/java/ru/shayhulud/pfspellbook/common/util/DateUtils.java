package ru.shayhulud.pfspellbook.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Утиль-класс для дат.
 */
public final class DateUtils {

	/**
	 * Стандартынй формат даты.
	 */
	public static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

	private DateUtils() {
	}

	public static OffsetDateTime fromDate(Date date) {
		if (date == null) {
			return null;
		}
		return OffsetDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	/**
	 * Перевод из epoch в OffsetDateTime средствами класса OffsetDateTime.
	 *
	 * @param timestamp epoch - время в секундах
	 * @return время в OffsetDateTime
	 */
	public static OffsetDateTime ofEpoch(Long timestamp) {
		if (timestamp == null) {
			return null;
		}
		return OffsetDateTime.of(
			LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC),
			ZoneOffset.UTC
		);
	}

	public static Date fromDateTime(OffsetDateTime finishDate) {
		if (finishDate == null) {
			return null;
		}
		return new Date(finishDate.toInstant().toEpochMilli());
	}

	public static Date fromUnix(Long unixTime) {
		if (unixTime == null) {
			return null;
		}
		return new Date(unixTime * 1000);
	}

	public static Date fromMills(Long mills) {
		if (mills == null) {
			return null;
		}
		return new Date(mills);
	}

	public static String fromUnixFormatted(Long unixTime) {
		if (unixTime == null) {
			return null;
		}

		return DateUtils.DEFAULT_DATE_FORMAT.format(fromUnix(unixTime));
	}

	public static String fromTimestampFormatted(Long timestamp) {
		if (timestamp == null) {
			return null;
		}

		return DateUtils.DEFAULT_DATE_FORMAT.format(timestamp);
	}

	public static Date getDateMinutesAfterNowBy(Long timeInMinutes) {
		return fromDateTime(
			OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES).plus(timeInMinutes, ChronoUnit.MINUTES)
		);
	}
}
