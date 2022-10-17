package com.sns.test;

import java.util.Map;

import org.apache.ibatis.io.ResolverUtil.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDAO {

	public Map<String, Object> getTest();
}
