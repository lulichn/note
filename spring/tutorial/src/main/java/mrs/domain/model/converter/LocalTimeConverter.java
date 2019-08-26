package mrs.domain.model.converter;

import javax.persistence.AttributeConverter;
import java.sql.Time;
import java.time.LocalTime;

public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {
	@Override
	public Time convertToDatabaseColumn(LocalTime attribute) {
		return attribute == null ? null : Time.valueOf(attribute);
	}

	@Override
	public LocalTime convertToEntityAttribute(Time dbData) {
		return dbData == null ? null : dbData.toLocalTime();
	}
}
