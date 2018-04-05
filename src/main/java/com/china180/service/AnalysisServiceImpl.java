package com.china180.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.china180.dao.RankDao;
import com.china180.dao.SoundDao;
import com.china180.dao.TagsDao;
import com.china180.dao.TopicWordsDao;

@Service
@Transactional
public class AnalysisServiceImpl implements AnalysisService {

	@Resource
	private SoundDao soundDao;

	@Resource
	private TagsDao tagsDao;

	@Resource
	private RankDao rankDao;

	@Resource
	private TopicWordsDao topicWordsDao;

	@Override
	public List<Map<String, Object>> findSoundByRange(Map<String, Object> map) {
		return soundDao.findByRange(map);
	}

	@Override
	public List<Map<String, Object>> findTagsByProjectId(Map<String, Object> map) {
		return tagsDao.findTagsByProjectId(map);
	}

	@Override
	public List<Map<String, Object>> findVipRankByProjectId(Map<String, Object> map) {
		return rankDao.findVipRankByProjectId(map);
	}

	@Override
	public List<Map<String, Object>> findVipInfo(Map<String, Object> map) {
		return rankDao.findVipInfo(map);
	}

	@Override
	public List<Map<String, Object>> findTopicWordsByProjectId(Map<String, Object> map) {
		return topicWordsDao.findTopicWordsByProjectId(map);
	}

}
