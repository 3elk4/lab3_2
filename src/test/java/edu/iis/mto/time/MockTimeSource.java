package edu.iis.mto.time;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Instant;

public class MockTimeSource implements TimeSource {
	private Instant instant;

	public MockTimeSource() {
		this.instant = Instant.now();
	}

	public void addHours(int numberOfHours) {
		this.instant = instant.plus(Duration.standardHours(numberOfHours));
	}

	@Override
	public DateTime now() {
		return instant.toDateTime();
	}
}
