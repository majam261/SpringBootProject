package hr.sportshall.displayinfo;

public class DisplayInfoHall implements IDisplayInfo {
	
	@Override
	public String displayInfo(String info) {
		return "Hall: " + info;
	}

}
