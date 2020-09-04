package deutche.test.db.read.write;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import deutche.test.trade.TradeDetails;

/**
 * 
 * @author RAchit
 *
 */
public class WriteUpdateRecords {
	
	private String COMMA_CHAR = ",";
	
	/**
	 * 
	 * @param td
	 * @return
	 */
	public boolean writeToFile(List<TradeDetails> td) {
		try {
		
			FileWriter fileWrite = new FileWriter("c:/Rachit/Trade-detail.csv");
			typeHeader(fileWrite);
			System.out.println("Header counted!");
			Iterator<TradeDetails> itr = td.iterator();
			while(itr.hasNext()) {
				TradeDetails trade = itr.next();
				fileWrite.append(trade.getTradeId()+COMMA_CHAR);
				fileWrite.append(trade.getVersion()+COMMA_CHAR);
				fileWrite.append(trade.getCounterPartyId()+COMMA_CHAR);
				fileWrite.append(trade.getBookId()+COMMA_CHAR);
				fileWrite.append(trade.getMaturityDate()+COMMA_CHAR);
				fileWrite.append(trade.getCreatedDate()+COMMA_CHAR);
				fileWrite.append(trade.getExpired()+"\n");
			}
			fileWrite.flush();
			fileWrite.close();
		}catch(Exception e) {
			System.err.println("WriteUpdateRecords "+e.getMessage());
			return false;
		}
		
		return true;
		
	}

	/**
	 * 
	 * @param fileWrite
	 */
	private void typeHeader(FileWriter fileWrite) {
		try {
			fileWrite.append("Trade Id" +COMMA_CHAR);
			fileWrite.append("Version"+COMMA_CHAR);
			fileWrite.append("Counter-PartyId"+COMMA_CHAR);
			fileWrite.append("Book-Id"+COMMA_CHAR);
			fileWrite.append("Maturity Date"+COMMA_CHAR);
			fileWrite.append("Created Date"+COMMA_CHAR);
			fileWrite.append("expired"+"\n");
		}catch(Exception e) {
			System.err.println(e);
		}
		
	}
}
