package hr.sportshall.displayinfo;

import org.springframework.stereotype.Service;

@Service
public class DisplayInfoAppoint implements IDisplayInfo{
	
	@Override
	public String displayInfo(String info) {
		return "Appointment: " + info;
	}

}
