package com.branch.model;

import java.util.List;

public interface BranchDAO_interface {
	public void insert(BranchVO branchVO); // 新增分店
	public void update(BranchVO branchVO); // 修改分店
	public BranchVO findByPK(String braID);   // 用PK找對應的分店
	public List<BranchVO> getAll();        // 取得全部份店
}
