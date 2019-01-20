package com.billboard.controller;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.billboard.model.*;

public class BBTimerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Timer timer;
	TimerTask task;

//	public void init() throws ServletException {
//		timer = new Timer();
//		task = new TimerTask() {
//			public void run() {
//				System.out.println("開始執行");
//				bbStatusChecker();
//			}
//		};
//		timer.scheduleAtFixedRate(task, 1000,   30*1000);
//		System.out.println("已建立輪播廣告排程!");
//	}

	public void destroy() {
		timer.cancel();
		timer.purge();
		System.out.println("已結束輪播廣告排程!");
	}

	public void bbStatusChecker() {
		BillboardService bbSvc = new BillboardService();
		List<BillboardVO> bbList = bbSvc.getAll();

		try {
			for (BillboardVO bbVO : bbList) {
				long now = System.currentTimeMillis();
				long up = bbVO.getbbStart().getTime();
				long down = bbVO.getbbEnd().getTime();

				if (now > up && now < down) {
					bbSvc.updateBB(bbVO.geturl(), bbVO.getpic(), bbVO.getbbStart(), bbVO.getbbEnd(), bbVO.getbbID(), 1);// 上架
				} else if (now > down) {
					bbSvc.updateBB(bbVO.geturl(), bbVO.getpic(), bbVO.getbbStart(), bbVO.getbbEnd(), bbVO.getbbID(), 2);// 下架
				} else {
					bbSvc.updateBB(bbVO.geturl(), bbVO.getpic(), bbVO.getbbStart(), bbVO.getbbEnd(), bbVO.getbbID(), 0);// 待上架
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}

}
