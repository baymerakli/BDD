package application;

public class OffHoursFalseMock implements Hours {

	@Override
	public boolean isOffHours() {
		return false;
	}

	public static Hours getInstance() {
		return new OffHoursFalseMock();
	}

}
