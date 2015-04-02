package zx.soft.api.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import zx.soft.api.dao.StatusMapper;
import zx.soft.api.domain.ErrorResponse;
import zx.soft.api.domain.PostData;
import zx.soft.api.persist.CreateTables;
import zx.soft.api.persist.IndexCloudSolr;
import zx.soft.api.util.PersistConstant;
import zx.soft.model.sentiment.RecordInfo;
import zx.soft.model.sentiment.RecordInsert;
import zx.soft.utils.checksum.CheckSumUtils;
import zx.soft.utils.log.LogbackUtil;
import zx.soft.utils.threads.ApplyThreadPool;

/**
 * 索引服务类
 *
 * @author wanggang
 *
 */
@Service
public class IndexService {

	private static Logger logger = LoggerFactory.getLogger(IndexService.class);

	@Inject
	private StatusMapper statusMapperImpl;

	@Inject
	private IndexCloudSolr indexCloudSolr;

	private static ThreadPoolExecutor pool = ApplyThreadPool.getThreadPoolExector();

	static {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				pool.shutdown();
			}
		}));
	}

	public ErrorResponse addIndexData(final PostData postData) {
		if (postData == null || postData.getRecords() == null) {
			logger.info("Records' size=0");
			return new ErrorResponse.Builder(-1, "no post data.").build();
		}
		logger.info("Records' Size:{}", postData.getRecords().size());
		try {
			if (postData.getRecords().size() > 0) {
				pool.execute(new Thread(new Runnable() {
					@Override
					public void run() {
						// 持久化到MySQL
						persist2MySQL(postData.getRecords());
						// 持久化到Solr
						persist2Solr(postData.getRecords());
					}
				}));
			}
			return new ErrorResponse.Builder(0, "ok").build();
		} catch (Exception e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
			return new ErrorResponse.Builder(-1, "persist error!").build();
		}
	}

	/**
	 * 数据持久化到Redis
	 */
	private void persist2Solr(List<RecordInfo> records) {
		try {
			indexCloudSolr.addDocsToSolr(records);
		} catch (Exception e) {
			logger.error("Exception:{}", LogbackUtil.expection2Str(e));
		}
	}

	/**
	 * 数据持久化到Mysql
	 */
	private void persist2MySQL(List<RecordInfo> records) {
		for (RecordInfo record : records) {
			try {
				statusMapperImpl.insertRecord(transRecord(record));
			} catch (Exception e) {
				logger.error("Exception:{}", LogbackUtil.expection2Str(e));
			}
		}
	}

	private RecordInsert transRecord(RecordInfo record) {
		return new RecordInsert.Builder(CreateTables.SENT_TABLE + CheckSumUtils.getCRC32(record.getId())
				% PersistConstant.STATUS_TABLE_COUNT, record.getId(), record.getPlatform()).setMid(record.getMid())
				.setUsername(record.getUsername()).setNickname(record.getNickname())
				.setOriginal_id(record.getOriginal_id()).setOriginal_uid(record.getOriginal_uid())
				.setOriginal_name(record.getOriginal_name()).setOriginal_title(record.getOriginal_title())
				.setOriginal_url(record.getOriginal_url()).setUrl(record.getUrl()).setHome_url(record.getHome_url())
				.setTitle(record.getTitle()).setType(record.getType()).setContent(record.getContent())
				.setComment_count(record.getComment_count()).setRead_count(record.getRead_count())
				.setFavorite_count(record.getFavorite_count()).setAttitude_count(record.getAttitude_count())
				.setRepost_count(record.getRepost_count()).setVideo_url(record.getVideo_url())
				.setPic_url(record.getPic_url()).setVoice_url(record.getVoice_url())
				.setTimestamp(new Date(record.getTimestamp())).setSource_id(record.getSource_id())
				.setLasttime(new Date(record.getLasttime())).setServer_id(record.getServer_id())
				.setIdentify_id(record.getIdentify_id()).setIdentify_md5(record.getIdentify_md5())
				.setKeyword(record.getKeyword()).setFirst_time(new Date(record.getFirst_time()))
				.setUpdate_time(new Date(record.getUpdate_time())).setIp(record.getIp())
				.setLocation(record.getLocation()).setGeo(record.getGeo()).setReceive_addr(record.getReceive_addr())
				.setAppend_addr(record.getAppend_addr()).setSend_addr(record.getSend_addr())
				.setSource_name(record.getSource_name()).setSource_type(record.getSource_type())
				.setCountry_code(record.getCountry_code()).setLocation_code(record.getLocation_code())
				.setProvince_code(record.getProvince_code()).setCity_code(record.getCity_code()).build();
	}

}
