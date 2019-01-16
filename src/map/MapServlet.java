package map;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.branch.model.BranchService;
import com.branch.model.BranchVO;

public class MapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public  void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public  void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if("getBranchLocation".equals(action)) {
			String braID  = req.getParameter("braID");
			System.out.println(braID);
			BranchService braSvc = new BranchService();
			BranchVO braVO = braSvc.getOneByID(braID);
			Double lat = braVO.getBraLat();
			Double lng = braVO.getBraLng();
			System.out.println(lat);
			System.out.println(lng);
			
			
			JSONObject jobj = new JSONObject();
			try {
				jobj.put("lat", lat);
				jobj.put("lng", lng);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(jobj.toString());
			out.flush();
			out.close();
		}
	}

}