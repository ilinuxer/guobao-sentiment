package zx.soft.api.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import zx.soft.api.util.PersistConstant;
import zx.soft.utils.checksum.CheckSumUtils;

public class InsertStatusTWTest {

	@Test
	public void testTablenameFacet() {
		assertEquals("status_info_twitter_6",
				PersistConstant.STATUS_INFO_TWITTER_PREFIX
						+ (CheckSumUtils.getCRC32(123456789 + "") % PersistConstant.STATUS_TABLE_COUNT));
		assertEquals("status_info_twitter_8",
				PersistConstant.STATUS_INFO_TWITTER_PREFIX
						+ (CheckSumUtils.getCRC32("abcdefgefht") % PersistConstant.STATUS_TABLE_COUNT));
	}

}
