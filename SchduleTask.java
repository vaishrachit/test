package deutche.test.store;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimerTask;

import deutche.test.db.read.write.ReadRecord;
import deutche.test.db.read.write.WriteUpdateRecords;
import deutche.test.trade.TradeDetails;

public class SchduleTask extends TimerTask {

	@Override
	public void run() {
		ReadRecord record = new ReadRecord();
		List<TradeDetails> list = record.readFromStoredFile();
		boolean checkExpiry = checkExpiry(list);
		if(checkExpiry) {
			WriteUpdateRecords write = new WriteUpdateRecords();
			boolean status = write.writeToFile(list);
			System.out.println("Updated Record Successfully!");
		}
	}

	private boolean checkExpiry(List<TradeDetails> list) {
		boolean updated = false;
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		for(int i=0;i<list.size();i++) {
			TradeDetails details = list.get(i);
			LocalDate storedDate = LocalDate.parse(details.getMaturityDate(),format);
			if(storedDate.isBefore(currentDate) && details.getExpired().equals("N")) {
				details.setExpired("Y");
				list.set(i, details);
				updated = true;
			}			
		}
		return updated;
	} 

}
