package deutche.test.store;

import java.util.Iterator;
import java.util.List;

import deutche.test.db.read.write.ReadRecord;
import deutche.test.trade.TradeDetails;

/**
 * 
 * @author Rachit
 *
 */
public class StoreOperations {

	public List<TradeDetails> checkTradeRecords(TradeDetails trade) {
		
		ReadRecord rr = new ReadRecord();
		List<TradeDetails> tradeDetails = rr.readFromStoredFile();
		Iterator<TradeDetails> itr = tradeDetails.iterator();
		boolean append = false;
		int counter = 0;
		while(itr.hasNext()) {			
			TradeDetails storedDetails = itr.next();
				if(storedDetails.getTradeId().equals(trade.getTradeId())) {
					append = true;
					if(storedDetails.getVersion() == trade.getVersion()) {
						TradeDetails details = new TradeDetails(trade.getTradeId(),trade.getVersion(),trade.getCounterPartyId(),trade.getBookId(),trade.getMaturityDate(),trade.getCreatedDate(),trade.getExpired());
						tradeDetails.set(counter, details);
						append = false;
					}else if(storedDetails.getVersion() < trade.getVersion()) {
						append = true;
					} else if(storedDetails.getVersion() > trade.getVersion()) {
						append = false;
						throw new RuntimeException("Can not store lower version of same Trade!");						
					}
				}
			counter++;
		}
		if(append) {
			tradeDetails.add(trade);
		}
		
		return tradeDetails;
	}
	
}
