package zx.soft.api.persist;

import zx.soft.api.util.PersistConstant;

/**
 * 创建舆情相关数据表，根据crc32校验码hash分表。
 *
 * @author wanggang
 *
 */
public class CreateTables {

	public static final String SENT_TABLE = "guobao_sent_records_";

	/**
	 * 主函数
	 */
	public static void main(String[] args) {

		SentDbcp sentDbcp = new SentDbcp();
		for (int i = 0; i < PersistConstant.STATUS_TABLE_COUNT; i++) {
			sentDbcp.createSentimentTable(CreateTables.SENT_TABLE + i);
		}
		sentDbcp.close();
		System.out.println("Finish!");

	}

}
