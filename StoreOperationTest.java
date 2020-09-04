package deutche.test.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;

import deutche.test.db.read.write.ReadRecord;
import deutche.test.trade.TradeDetails;

/**
 * 
 * @author Rachit
 *
 */
public class StoreOperationTest {

	
	@Test(expected = RuntimeException.class)
	public void testStoreRecordsforLessVersion() {
		TradeDetails details = new TradeDetails("T2",0,"CP-2","B2","20-05-2023","04-09-2020","N");
		
		
		StoreOperations operations = new StoreOperations();
		operations.checkTradeRecords(details);
		
	}
		
	@Test
	public void testStoreRecordsforUpdatedVersion() {
		TradeDetails details = new TradeDetails("T2",4,"CP-2","B2","20-05-2023","04-09-2020","N");
		ReadRecord readOperation = new ReadRecord();
		List<TradeDetails> list = readOperation.readFromStoredFile();
		
		StoreOperations operations = new StoreOperations();
		assertNotEquals(list,operations.checkTradeRecords(details));
		
	}


}
