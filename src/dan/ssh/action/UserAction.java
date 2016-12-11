package dan.ssh.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dan.ssh.model.User;
import dan.ssh.service.UserService;

public class UserAction extends ActionSupport {

	private User user;
	private UserService userService;
	private Map<String, Object> request;
	private Map<String, Object> session;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//将查询的所有结果放到上下文中
	public String list() {
		request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("users", userService.queryAll());
		return "success";
	}
	
	//登录检查
	public String checkUser() {
		boolean flag = userService.checkUser(user);
		if (flag) {
			//将当前user对象放到session中
			session = ActionContext.getContext().getSession();
			session.put("user1", user);
			//返回所有结果显示在页面
			return list();
		}
		return "error";
	}
	
	//删除用户，之后再返回所有查询结果动态显示在页面上
	public String deleteUser() {
		userService.deleteUser(user.getId());
		return list();
	}
	
	//添加用户
	public String addUser() {
		userService.addUser(user);
		return list();
	}
	
	//修改用户信息，通过id找到要修改的用户
	//并返回"update"字符串，用以跳转到修改页面
	public String updateUser() {
	    user = userService.queryById(user.getId());
	    return "update";
	}
	  
	// 在修改页面完成修改，调用service的update
	// 之后再返回所有查询结果动态显示在页面上
	public String updateUserImpl() {
	    userService.updateUser(user);
	    return list();
	}

    // 根据条件查询用户，查询结果放到上下文中
    // tag 用以标记，后面在 jsp 页面有体现
	public String queryUser() {
	    request = (Map)ActionContext.getContext().get("request");
	    request.put("users2", userService.queryUser(user));
	    request.put("tag", 2);
	    return list();
	}
}

