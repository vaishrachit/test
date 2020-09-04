package deutche.test.db.read.write;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import deutche.test.trade.TradeDetails;
/**
 * 
 * @author Rachit
 *
 */
public class ReadRecord {

	/**
	 * 
	 * @return
	 */
	public TradeDetails readFromInputFile() {

		TradeDetails trade = new TradeDetails();

		Path pathforInputFile = Paths.get("c:/Rachit/Record1.csv");
		
		try (BufferedReader br = Files.newBufferedReader(pathforInputFile, StandardCharsets.US_ASCII)) {

			String line = br.readLine();
			while (line != null) {

				String[] attributes = line.split(",");
				trade = createTrade(attributes);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return trade;
	}

	public void processGivenRecord(TradeDetails tradeDetails) {
		
		List<TradeDetails> tradeDe = readFromStoredFile();
		
	}
	
	public List<TradeDetails> readFromStoredFile() {

		List<TradeDetails> listTrade = new ArrayList<>();

		Path pathToFile = Paths.get("c:/Rachit/Trade-detail.csv");
		
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			String header = br.readLine();
			String line = br.readLine();
			while (line != null) {

				String[] attributes = line.split(",");
				TradeDetails trade = createTrade(attributes);
				listTrade.add(trade);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return listTrade;
	}

	private static TradeDetails createTrade(String[] metadata) {
		String trade_id = metadata[0];
		int version = Integer.parseInt(metadata[1]);
		String counter_partyId = metadata[2];
		String bookId = metadata[3];
		String maturity_date = metadata[4];
		String createdDate = metadata[5];
		String expired = metadata[6];

		return new TradeDetails(trade_id, version, counter_partyId, bookId, maturity_date, createdDate, expired);

	}

}
