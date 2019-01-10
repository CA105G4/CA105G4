package com.activity.model;

import java.util.List;
import java.util.Set;

import com.activityDetail.model.ActivityDetailVO;

public interface ActivityDAO_interface {
		
	public void insert(ActivityVO activityVO); //新增促銷活動
	public void update(ActivityVO activityVO); //修改促銷活動
	public void delete(String actID);
	public ActivityVO findByPK(String actID); //用PK找到對應的促銷活動
	public ActivityVO findByName(String actName); //用活動名稱找到對應促銷活動
	public List<ActivityVO> getAll(); //取得全部促銷活動之列表
	
	/**[Gina]訂單計算總金額-查找促銷日期折扣**/
	public float getActivityDiscount(String rtId, String date);
	/**[Gina]訂單計算總金額-查找促銷日期折扣**/	
	
	public void insertWithDetail(ActivityVO actVO,List<ActivityDetailVO>list);
	public Set<ActivityDetailVO> getDetailByactID(String actID);           //查詢該活動明細(一對多)(回傳 Set)
}