package com.billboard.model;

import java.util.List;

import com.billboard.model.BillboardVO;

public interface BillboardDAO_interface {
	public void insert(BillboardVO billboardVO); //新增一個輪播廣告
	public void update(BillboardVO billboardVO); //修改一個輪播廣告
	public void delete(Integer bbID);//刪除一個輪播廣告
	public BillboardVO findByPK(Integer bbID); //透過主鍵找到輪播廣告
	public  List<BillboardVO>getAll();
}
