package com.branch.model;

import java.util.List;

public class BranchService {
	private BranchDAO_interface dao;

	public BranchService() {
		dao = new BranchDAO();
	}

	public BranchVO addBra(String braName, String braIntro, byte[] braPic, String braTel, byte[] braVideo,
			String braAddr, Double braLng, Double braLat, Integer braState) {
		BranchVO braVO = new BranchVO();

		braVO.setBraName(braName);
		braVO.setBraIntro(braIntro);
		braVO.setBraPic(braPic);
		braVO.setBraTel(braTel);
		braVO.setBraVideo(braVideo);
		braVO.setBraAddr(braAddr);
		braVO.setBraLng(braLng);
		braVO.setBraLat(braLat);
		braVO.setBraState(braState);
		dao.insert(braVO);

		return braVO;
	}
	
	public BranchVO updateBra(String braName, String braIntro, byte[] braPic, String braTel, byte[] braVideo,
			String braAddr, Double braLng, Double braLat, Integer braState,String braID) {
		BranchVO braVO = new BranchVO();

		braVO.setBraName(braName);
		braVO.setBraIntro(braIntro);
		braVO.setBraPic(braPic);
		braVO.setBraTel(braTel);
		braVO.setBraVideo(braVideo);
		braVO.setBraAddr(braAddr);
		braVO.setBraLng(braLng);
		braVO.setBraLat(braLat);
		braVO.setBraState(braState);
		braVO.setBraID(braID);
		dao.update(braVO);
		
		return braVO;
	}
	
	public BranchVO getOneByID(String braID) {
		return dao.findByPK(braID);
	}
	
	public List<BranchVO>getAll(){
		return dao.getAll()	;
				}
	
	
	
}
