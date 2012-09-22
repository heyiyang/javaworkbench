package inters;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class Admin extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		// TODO Auto-generated method stub
		ActionContext ac = ai.getInvocationContext();
		Map<String, Object> session = ac.getSession();
		System.out.println(session.get("isAdmin"));
		short isAdmin = (Short) session.get("isAdmin");
		if(isAdmin != 0) {
			ai.invoke();
		}else{
			ac.put("msg", "�Բ�����û�й���ԱȨ�ޣ����������Ա�˺�");
			return "error";
		}
		return null;
	}

}
