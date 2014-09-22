package application;

public class OffHoursTrueMock implements Hours {

	@Override
	public boolean isOffHours() {
		return true;
	}

	public static Hours getInstance() {
		return new OffHoursTrueMock();
	}
}
