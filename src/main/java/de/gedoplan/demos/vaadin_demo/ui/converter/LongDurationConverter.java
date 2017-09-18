package de.gedoplan.demos.vaadin_demo.ui.converter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

public class LongDurationConverter implements Converter<Long, Duration> {

	@Override
	public Result<Duration> convertToModel(Long value, ValueContext context) {
		if(value == null) {
			return Result.ok(null);
		}
		return Result.ok(Duration.of(value * 60,ChronoUnit.SECONDS));
	}

	@Override
	public Long convertToPresentation(Duration value, ValueContext context) {
		if(value == null) {
			return null;
		}
		return value.toMinutes();
	}

}
