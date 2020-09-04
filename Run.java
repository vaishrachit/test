package deutche.test.run;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import deutche.test.db.read.write.ReadRecord;
import deutche.test.db.read.write.WriteUpdateRecords;
import deutche.test.store.SchduleTask;
import deutche.test.store.StoreOperations;
import deutche.test.trade.TradeDetails;

public class Run {
	
	public static void main(String[] args) {

		ReadRecord record = new ReadRecord();
		TradeDetails list = record.readFromInputFile();

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		LocalDate currentDate = LocalDate.now();
		LocalDate givenDate = LocalDate.parse(list.getMaturityDate(), format);

		if (givenDate.isBefore(currentDate)) {
			System.out.println("Can not perform Trading on Expired Items!");
			return;
		}

		StoreOperations operations = new StoreOperations();
		WriteUpdateRecords write = new WriteUpdateRecords();

		List<TradeDetails> finalRecords = operations.checkTradeRecords(list);

		System.out.println("Records =" + finalRecords);

		boolean status = write.writeToFile(finalRecords);

		System.out.println("Records Writen Status is " + status);

		Date date = new Date();
		date.setHours(23);
		Timer timer = new Timer();
		timer.schedule(new SchduleTask(), date);
		
	}

}
